package com.example.finalapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoticiasGuardadas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticiasAdapter noticiasAdapter;
    private NoticiasDao noticiasDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_guardadas); // Asegúrate de tener este layout creado

        recyclerView = findViewById(R.id.recyclerViewNoticiasGuardadas); // ID del RecyclerView en tu layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticiasDao = new NoticiasDao(this); // Inicialización del DAO para acceder a las noticias guardadas
        List<Article> noticiasGuardadas = noticiasDao.obtenerNoticiasGuardadas(); // Obtener las noticias guardadas

        noticiasAdapter = new NoticiasAdapter(this, noticiasGuardadas, article -> {
            Intent intent = new Intent(NoticiasGuardadas.this, NoticiaDetallesActivity.class);
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
    }
}
