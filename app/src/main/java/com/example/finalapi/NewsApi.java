package com.example.finalapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface NewsApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}
