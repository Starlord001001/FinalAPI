package com.example.finalapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
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
        Call<NewsResponse> newsCall = newsApi.getTopHeadlines("es", apiKey);

        Log.d("API Call", "Antes de la llamada a la API");

        newsCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (!response.isSuccessful()) {
                    Log.e("API Error", "Código HTTP: " + response.code());
                    return;
                }

                NewsResponse newsResponse = response.body();
                if (newsResponse != null) {
                    Log.d("API Response", "Número de artículos: " + newsResponse.getTotalResults());
                }

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
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("API Failure", "Error: " + t.getLocalizedMessage());
                texto.setText("Problemas graves: " + t.getLocalizedMessage());
            }
        });

        Log.d("API Call", "Después de la llamada a la API");

    }

}
