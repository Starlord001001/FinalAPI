package com.example.finalapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("everything")
    Call<NewsResponse> getEverything(
            @Query("apiKey") String apiKey,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("q") String q
    );
}
