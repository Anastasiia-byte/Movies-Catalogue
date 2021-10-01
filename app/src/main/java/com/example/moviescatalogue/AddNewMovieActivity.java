package com.example.moviescatalogue;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moviescatalogue.databases.AppDatabase;
import com.example.moviescatalogue.databases.Movie;

public class AddNewMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_movie);

        final EditText name = findViewById(R.id.movieName);
        final EditText genre = findViewById(R.id.movieGenre);
        final EditText description = findViewById(R.id.movieDescription);

        Button addBtn = findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMovie(
                        name.getText().toString(),
                        genre.getText().toString(),
                        description.getText().toString()
                );
            }
        });
    }

    private void addNewMovie(String name, String genre, String description) {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());

        Movie movie = new Movie();
        movie.setName(name);
        movie.setGenre(genre);
        movie.setDescription(description);
        movie.setRate(0);
        movie.setWatched(false);

        db.movieDao().insertMovie(movie);
        finish();
    }
}