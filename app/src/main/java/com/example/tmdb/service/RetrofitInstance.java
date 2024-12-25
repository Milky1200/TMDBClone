package com.example.tmdb.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static volatile Retrofit retrofit=null;
    private static final String BASE_URL="http://api.themoviedb.org/3/";
    public static MovieDataService getService(){
        if(retrofit==null){
            synchronized (RetrofitInstance.class){
                if(retrofit==null){
                    retrofit=new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(BASE_URL)
                            .build();
                }
            }
        }
        return retrofit.create(MovieDataService.class);

    }

}
