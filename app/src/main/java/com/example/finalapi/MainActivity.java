package com.example.finalapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = findViewById(R.id.tv_result);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi jasonPlaceApi = retrofit.create(NewsApi.class);

        Call<List<Post>> posts = jasonPlaceApi.getPosts();

        posts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    texto.setText("Problemas, codigo http: " +response.code());
                    return;
                }
                List<Post> body = response.body();
                for (Post post: body){
                    String txt = "";
                    txt += "ID: " + post.getId() + "\n";
                    txt += "User ID: " + post.getUserId() + "\n";
                    txt += "Title: " + post.getTitle() + "\n";
                    txt += "Body: " + post.getBody() + "\n";

                    texto.setText(txt);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                texto.setText("Problemas graves: " +t.getLocalizedMessage());
            }
        });
    }
}