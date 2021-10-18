package com.example.moviescatalogue;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moviescatalogue.databases.Movie;

public class AddMovieFragment extends Fragment {

    private AddMovieViewModel mViewModel;
    private View view;

    public static AddMovieFragment newInstance() {
        return new AddMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_movie_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(AddMovieViewModel.class);

        final EditText name = view.findViewById(R.id.movieName);
        final EditText genre = view.findViewById(R.id.movieGenre);
        final EditText description = view.findViewById(R.id.movieDescription);

        Button addBtn = view.findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new Movie();
                movie.setName(name.getText().toString());
                movie.setGenre(genre.getText().toString());
                movie.setDescription(description.getText().toString());
                movie.setRate(0);
                movie.setWatched(false);

                mViewModel.addMovie(movie);

                name.clearComposingText();
                genre.clearComposingText();
                description.clearComposingText();

            }
        });

    }

}