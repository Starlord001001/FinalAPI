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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")  // URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        String apiKey = "43e06f4cb44f47a29383d7f97765b0bb"; //KEY
        Call<com.example.finalapi.Response> newsCall = newsApi.getTopHeadlines(apiKey);

        newsCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                if (!response.isSuccessful()) {
                    texto.setText("Problemas, código HTTP: " + response.code());
                    return;
                }

                Response newsResponse = response.body();
                List<Article> articles = newsResponse.getArticles();

                StringBuilder txt = new StringBuilder();
                for (Article article : articles) {
                    txt.append("Source: ").append(article.getSource().getName()).append("\n");
                    txt.append("Author: ").append(article.getAuthor()).append("\n");
                    txt.append("Title: ").append(article.getTitle()).append("\n");
                    txt.append("Description: ").append(article.getDescription()).append("\n");
                    txt.append("URL: ").append(article.getUrl()).append("\n");
                    txt.append("\n");
                }

                texto.setText(txt.toString());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                texto.setText("Problemas graves: " + t.getLocalizedMessage());
            }
        });
    }
}
