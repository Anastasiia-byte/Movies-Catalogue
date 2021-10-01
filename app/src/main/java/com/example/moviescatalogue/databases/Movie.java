package com.example.moviescatalogue.databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    @Ignore
    public int id;
    public String name;
    public String genre;
    public String description;
    public int rate;

    @ColumnInfo(name = "is_watched")
    public boolean isWatched;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }
}
