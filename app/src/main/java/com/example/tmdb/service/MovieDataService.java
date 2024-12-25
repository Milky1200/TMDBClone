package com.example.tmdb.service;

import com.example.tmdb.model.MovieDBResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {
    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apikey);
    @GET("movie/popular")
    Observable<MovieDBResponse> getPopularMoviesRx(@Query("api_key") String apikey);
}
