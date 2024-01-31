package com.example.finalapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<Response> getTopHeadlines(@Query("apiKey") String apiKey);
}
