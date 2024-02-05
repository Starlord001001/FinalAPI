package com.example.finalapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        texto = findViewById(R.id.tv_result);

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
                        txt.append("Source: ").append(article.getSource().getName()).append("\n");
                        txt.append("Author: ").append(article.getAuthor()).append("\n");
                        txt.append("Title: ").append(article.getTitle()).append("\n");
                        txt.append("Description: ").append(article.getDescription()).append("\n");
                        txt.append("URL: ").append(article.getUrl()).append("\n");
                        txt.append("\n");
                    }

                    texto.setText(txt.toString());
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
