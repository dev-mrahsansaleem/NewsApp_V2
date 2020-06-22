package com.example.newsapp_v2.API;

import com.example.newsapp_v2.Model.NewsRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsApi {

    @GET("top-headlines")
    Call<NewsRes> getArticals(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}
