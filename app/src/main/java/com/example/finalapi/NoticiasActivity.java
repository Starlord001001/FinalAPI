package com.example.finalapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticiasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticiasAdapter noticiasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticiasAdapter = new NoticiasAdapter();
        recyclerView.setAdapter(noticiasAdapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String q = extras.getString("q");
            Integer pageSize = Integer.parseInt(extras.getString("pageSize"));
            String language = extras.getString("language");
            String sortBy = extras.getString("sortBy");

            // Realiza la solicitud a la API y muestra las noticias aquí
            fetchNews(q, pageSize, language, sortBy);
        }
    }

    private void fetchNews(String q, Integer pageSize, String language, String sortBy) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")  // URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        String apiKey = "43e06f4cb44f47a29383d7f97765b0bb"; // KEY


        Call<NewsResponse> newsCall = newsApi.getEverything(apiKey, q, pageSize,language, sortBy);

        newsCall.enqueue(new Callback<NewsResponse>() {

            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                Log.d("NoticiasActivity", "URL completa: " + call.request().url());

                if (!response.isSuccessful()) {
                    String errorMessage = "Error al obtener noticias. Código HTTP: " + response.code();
                    if (response.errorBody() != null) {
                        try {
                            errorMessage += "\n" + response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e("NoticiasActivity", errorMessage);
                    return;
                }

                NewsResponse newsResponse = response.body();
                if (newsResponse != null) {
                    List<Article> articles = newsResponse.getArticles();

                    StringBuilder txt = new StringBuilder();
                    for (Article article : articles) {
                        txt.append("Title: ").append(article.getTitle()).append("\n");
                        txt.append("URL To Image: ").append(article.getUrlToImage()).append("\n");
                        txt.append("\n");
                    }

                    noticiasAdapter.setNoticias(articles);
                } else {
                    Log.e("NoticiasActivity", "Respuesta de noticias vacía o nula.");
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                String errorMessage = "Error al realizar la solicitud: " + t.getLocalizedMessage();
                Log.e("NoticiasActivity", errorMessage);
                t.printStackTrace(); // Imprime la traza para obtener más detalles en el log
            }

        });
    }
}
