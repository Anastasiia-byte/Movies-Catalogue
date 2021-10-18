package com.example.moviescatalogue;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.moviescatalogue.databases.Movie;

public class AddMovieViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;

    public AddMovieViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

}