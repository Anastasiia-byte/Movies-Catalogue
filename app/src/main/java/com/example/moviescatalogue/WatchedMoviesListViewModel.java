package com.example.moviescatalogue;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviescatalogue.databases.Movie;

import java.util.List;

public class WatchedMoviesListViewModel extends AndroidViewModel {
    private final LiveData<List<Movie>> moviesList;
    private final MovieRepository movieRepository;

    public WatchedMoviesListViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        moviesList = movieRepository.getAllWatched();
    }

    public LiveData<List<Movie>> getData(){
        return moviesList;
    }
}