package com.example.moviescatalogue;

import android.os.Bundle;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        name = findViewById(R.id.selectedMovieName);
        genre = findViewById(R.id.selectedMovieGenre);
        descr = findViewById(R.id.selectedMovieDescription);
        slider = findViewById(R.id.rateSlider);



        getIncomingIntent();

    }


    private void getIncomingIntent(){
        if(getIntent().hasExtra("movie_name")){
            String movieName = getIntent().getStringExtra("movie_name");
            getMovieObj(movieName);
            getRate(movieName);
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

    private void getRate(final String movieName){
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                int rateValue = Math.round(value);
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                db.movieDao().changeRate(rateValue, movieName);
            }
        });
    }

}
