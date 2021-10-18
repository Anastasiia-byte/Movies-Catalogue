package com.example.moviescatalogue;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviescatalogue.databases.Movie;

import java.util.List;
import java.util.Objects;

public class MoviesListFragment extends Fragment {

    private MoviesListViewModel mViewModel;
    private View view;
    private MoviesListAdapter moviesListAdapter;
    private MoviesListAdapter.RecyclerViewClickListener listener;
    private List<Movie> moviesList;

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movies_list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();

        mViewModel = new ViewModelProvider(this).get(MoviesListViewModel.class);
        mViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesList = movies;
                moviesListAdapter.setMovieList(movies);
            }
        });

    }

    private void initRecyclerView(){
        setOnClickListener();
        RecyclerView recyclerView = view.findViewById(R.id.moviesList);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        moviesListAdapter = new MoviesListAdapter(view.getContext(), listener);
        recyclerView.setAdapter(moviesListAdapter);
    }

    private void setOnClickListener(){
        listener = (v, position) -> {
            Fragment movieInfo = new MovieFragment();

            MovieViewModel.selectMovie(moviesList.get(position).name);
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, movieInfo).commit();
        };



    }


}