package com.example.moviescatalogue;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviescatalogue.databases.AppDatabase;
import com.example.moviescatalogue.databases.Movie;

import java.util.List;

public class WatchedMoviesListActivity extends AppCompatActivity {
    private MoviesListAdapter moviesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched_movies_list);

        initRecyclerView();

        loadWatchedMovies();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.watchedMoviesList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        moviesListAdapter = new MoviesListAdapter(this);
        recyclerView.setAdapter(moviesListAdapter);
    }

    private void loadWatchedMovies(){
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        List<Movie> moviesList = db.movieDao().getAllWatched();
        moviesListAdapter.setMovieList(moviesList);
    }
}


