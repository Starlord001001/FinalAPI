package com.example.finalapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoticiasGuardadas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticiasAdapter noticiasAdapter;
    private NoticiasDAO noticiasDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_guardadas);

        FloatingActionButton fabBack = findViewById(R.id.fab_back3);
        fabBack.setImageResource(R.drawable.volver);
        fabBack.setOnClickListener(view -> onBackPressed());

        recyclerView = findViewById(R.id.recyclerViewNoticiasGuardadas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoticiasDataBase db = NoticiasDataBase.getDatabase(this);
        noticiasDao = db.noticiasDao();

        new Thread(() -> {
            List<Article> noticiasGuardadas = noticiasDao.getAllArticles();
            runOnUiThread(() -> {
                // Actualiza el adapter del RecyclerView en el hilo de UI
                noticiasAdapter = new NoticiasAdapter(this, noticiasGuardadas, article -> {
                    Intent intent = new Intent(NoticiasGuardadas.this, NoticiaDetallesActivity.class);
                    // Configura los extras del intent
                    intent.putExtra("autor", article.getAuthor());
                    intent.putExtra("titulo", article.getTitle());
                    intent.putExtra("descripcion", article.getDescription());
                    intent.putExtra("url", article.getUrl());
                    intent.putExtra("imagen_url", article.getUrlToImage());
                    intent.putExtra("publicadoEn", article.getPublishedAt());
                    intent.putExtra("contenido", article.getContent());
                    startActivity(intent);
                });

                recyclerView.setAdapter(noticiasAdapter);
            });
        }).start();
    }
}
