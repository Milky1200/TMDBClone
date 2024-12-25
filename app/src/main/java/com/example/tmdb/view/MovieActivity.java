package com.example.tmdb.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdb.databinding.ContentMovieBinding;
import com.example.tmdb.model.Movie;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tmdb.databinding.ActivityMovieBinding;

import com.example.tmdb.R;

public class MovieActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMovieBinding binding;
    private ContentMovieBinding binding2;
    TextView tvAdult, tvGenreIds, tvOriginalLanguage, tvOverview, tvPopularity,
            tvOriginalTitle, tvReleaseDate, tvVoteAverage,tvTitle;
    Movie movie;
    String movieImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMovieBinding.inflate(getLayoutInflater());
        //binding2= ContentMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.collapsingToolbar.setTitleEnabled(true);

        // Initialize the views using findViewById
        tvAdult = findViewById(R.id.tvAdult);
        tvTitle=findViewById(R.id.tvTitle);
        tvGenreIds = findViewById(R.id.tvGenreIds);
        tvOriginalLanguage = findViewById(R.id.tvOriginalLanguage);
        tvOverview = findViewById(R.id.tvOverview);
        tvPopularity = findViewById(R.id.tvPopularity);
        tvOriginalTitle = findViewById(R.id.tvOriginalTitle);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvVoteAverage = findViewById(R.id.tvVoteAverage);
        Intent intent=getIntent();
        if(intent.hasExtra("movie")){
            movie=getIntent().getParcelableExtra("movie");
            Toast.makeText(getApplicationContext(),movie.getOriginalTitle(),Toast.LENGTH_LONG).show();
            movieImageUri="https://image.tmdb.org/t/p/w500/"+movie.getPosterPath();

            binding.toolbar.setTitle(movie.getOriginalTitle());
            binding.collapsingToolbar.setTitle(movie.getOriginalTitle());
            tvTitle.setText(movie.getTitle().toString());
            tvAdult.setText(movie.getAdult() ? "Adult Content: "+"Yes" : "Adult Content: "+"No");
            tvGenreIds.setText("Genre: "+movie.getGenreIds().toString());
            tvOriginalLanguage.setText("Original Language: "+movie.getOriginalLanguage().toString());
            tvOverview.setText("Overview: "+movie.getOverview().toString());
            tvPopularity.setText("Popularity: "+movie.getPopularity().toString());
            tvOriginalTitle.setText("Original Title: "+movie.getOriginalTitle().toString());
            tvReleaseDate.setText("Release Date: "+movie.getReleaseDate().toString());
            tvVoteAverage.setText("Vote: "+movie.getVoteAverage().toString());

        }
        binding.toolbar.setTitle(movie.getOriginalTitle().toString());
        Glide.with(getApplicationContext()).load(movieImageUri).placeholder(R.drawable.place_holder)
                .into(binding.ivMovie);


    }
}