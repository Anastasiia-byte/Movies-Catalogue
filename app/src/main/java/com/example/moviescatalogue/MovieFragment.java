package com.example.moviescatalogue;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.moviescatalogue.databases.AppDatabase;
import com.example.moviescatalogue.databases.Movie;
import com.google.android.material.slider.Slider;

import java.util.List;

public class MovieFragment extends Fragment {

    private MovieViewModel mViewModel;
    private TextView name;
    private TextView genre;
    private TextView descr;
    private Slider slider;
    private Button rateMovie;
    private View view;
    private List<Movie> movieList;

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                setInfo(movies.get(0));
            }
        });


    }

    private void initViews() {
        name = view.findViewById(R.id.selectedMovieName);
        genre = view.findViewById(R.id.selectedMovieGenre);
        descr = view.findViewById(R.id.selectedMovieDescription);
        slider = view.findViewById(R.id.rateSlider);
        rateMovie = view.findViewById(R.id.rateMovieBtn);
    }

    private void setInfo(Movie movie){
        name.setText(movie.name);
        genre.setText(movie.genre);
        descr.setText(movie.description);
        slider.setValue(movie.rate);
        setRate();
    }

    private void setRate(){
        rateMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rate = Math.round(slider.getValue());
                mViewModel.changeRate(rate);
            }
        });
    }
}