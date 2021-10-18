package com.example.moviescatalogue;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviescatalogue.databases.AppDatabase;
import com.example.moviescatalogue.databases.Movie;
import com.example.moviescatalogue.databases.MovieDao;

import java.util.List;

public class MoviesListViewModel extends AndroidViewModel {
    private final LiveData<List<Movie>> moviesList;
    private final MovieRepository movieRepository;

    public MoviesListViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        moviesList = movieRepository.getAllUnWatched();
    }

    public LiveData<List<Movie>> getData(){
        return moviesList;
    }
}