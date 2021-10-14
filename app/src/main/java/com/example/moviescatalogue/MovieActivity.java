package com.example.moviescatalogue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviescatalogue.databases.AppDatabase;
import com.example.moviescatalogue.databases.Movie;
import com.google.android.material.slider.Slider;

import java.util.List;

public class MovieActivity extends AppCompatActivity {
    private TextView name;
    private TextView genre;
    private TextView descr;
    private Slider slider;
    private Button rateMovie;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        name = findViewById(R.id.selectedMovieName);
        genre = findViewById(R.id.selectedMovieGenre);
        descr = findViewById(R.id.selectedMovieDescription);
        slider = findViewById(R.id.rateSlider);
        rateMovie = findViewById(R.id.rateMovieBtn);

        getIncomingIntent();

    }


    private void getIncomingIntent(){
        if(getIntent().hasExtra("movie_name")){
            String movieName = getIntent().getStringExtra("movie_name");
            getMovieObj(movieName);
            setRate(movieName);
        }
    }

    private void getMovieObj(String name){
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        List<Movie> movieList = db.movieDao().findByName(name);
        setInfo(movieList.get(0));
    }

    private void setInfo(Movie movie){
        name.setText(movie.name);
        genre.setText(movie.genre);
        descr.setText(movie.description);
        slider.setValue(movie.rate);
    }

    private void setRate(final String movieName){
        rateMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rate = Math.round(slider.getValue());
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                db.movieDao().changeRate(rate, movieName);
            }
        });
    }

}
