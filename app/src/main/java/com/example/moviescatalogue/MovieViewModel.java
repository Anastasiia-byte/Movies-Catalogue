package com.example.moviescatalogue;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviescatalogue.databases.Movie;

import java.util.List;

public class MovieViewModel extends AndroidViewModel{
    private final static MutableLiveData<String> selectedMovie = new MutableLiveData<String>();
    private final MovieRepository movieRepository;
    private final LiveData<List<Movie>> moviesList;

    public MovieViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        moviesList = movieRepository.getMovieByName(selectedMovie.getValue());
    }

    public static void selectMovie(String movieName) {
        selectedMovie.setValue(movieName);
    }

    public LiveData<List<Movie>> getData() {
        return moviesList;
    }

    public void changeRate(int rate){
        movieRepository.changeRate(rate, selectedMovie.getValue());
    }
}