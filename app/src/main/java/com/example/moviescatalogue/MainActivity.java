package com.example.moviescatalogue;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moviescatalogue.databases.AppDatabase;
import com.example.moviescatalogue.databases.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MoviesListAdapter moviesListAdapter;
    private MoviesListAdapter.RecyclerViewClickListener listener;

    private ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        loadMovies();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        loadMovies();

        Button addBtn = findViewById(R.id.addMovieButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewMovieActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });

        Button watchedBtn = findViewById(R.id.watchedMoviesButton);
        watchedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WatchedMoviesListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView(){
        setOnClickListener();
        RecyclerView recyclerView = findViewById(R.id.moviesList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        moviesListAdapter = new MoviesListAdapter(this, listener);
        recyclerView.setAdapter(moviesListAdapter);
    }

    private void setOnClickListener() {
        final AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        final List<Movie> moviesList = db.movieDao().getAll();
        listener = new MoviesListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                intent.putExtra("movie_name", moviesList.get(position).name);
                startActivity(intent);
            }
        };
    }

    private void loadMovies(){
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        List<Movie> moviesList = db.movieDao().getAll();
        moviesListAdapter.setMovieList(moviesList);
    }
}