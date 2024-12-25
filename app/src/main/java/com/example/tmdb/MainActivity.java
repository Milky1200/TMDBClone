package com.example.tmdb;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieDBResponse;
import com.example.tmdb.service.MovieDataService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movies;
private static final String APIKEY="24381b07507d61664234bfa4f4ae29b0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getPopularMovies();
    }

    public void getPopularMovies() {
        MovieDataService service= RetrofitInstance.getService();
        Call<MovieDBResponse> responseCall=service.getPopularMovies(APIKEY);
        responseCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse=response.body();
                if(movieDBResponse!=null && movieDBResponse.getResults()!=null){
                    movies=(ArrayList<Movie>) movieDBResponse.getResults();

                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                Log.e("Failed", "onFailure: No response" );
            }
        });
    }
}