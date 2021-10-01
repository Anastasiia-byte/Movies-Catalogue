package com.example.moviescatalogue.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    List<Movie> getAll();

    @Query("SELECT * FROM movies WHERE is_watched = 1")
    List<Movie> getAllWatched();

    @Query("SELECT * FROM movies WHERE rowid IN (:movieIds)")
    List<Movie> loadAllByIds(int[] movieIds);

    @Query("SELECT * FROM movies WHERE name LIKE :name")
    List<Movie> findByName(String name);

    @Query("UPDATE movies SET rate = :newRate, is_watched = 1 WHERE name = :name")
    void changeRate(int newRate, String name);

    @Insert
    void insertAll(Movie... movies);

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void delete(Movie movie);

    @Update
    void updateUsers(Movie... movies);
}
