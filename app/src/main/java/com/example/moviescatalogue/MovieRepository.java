package com.example.moviescatalogue;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.moviescatalogue.databases.AppDatabase;
import com.example.moviescatalogue.databases.Movie;

import java.util.List;

public class MovieRepository {
    private AppDatabase db;

    public MovieRepository(Application application){
        db = AppDatabase.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Movie>> getAllMovies(){
        return db.movieDao().getAll();
    }

    public LiveData<List<Movie>> getAllUnWatched(){
        return db.movieDao().getAllUnWatched();
    }

    public LiveData<List<Movie>> getMovieByName(String name){
        return db.movieDao().findByName(name);
    }

    public void changeRate(int newRate, String name) {
        db.movieDao().changeRate(newRate, name);
    }

    public void addMovie(Movie movie) {
        db.movieDao().insertAll(movie);
    }

    public LiveData<List<Movie>> getAllWatched() {
        return db.movieDao().getAllWatched();
    }
}
