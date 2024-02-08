package com.example.finalapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class NoticiaDetallesActivity extends AppCompatActivity {

    private ImageView imagenNoticiaDetalle;
    private TextView tvAutor, tvTitulo, tvDescripcion, tvURL, tvPublicadoEn, tvContenido;
    private Button btnAccionNoticia;
    private NoticiasDAO noticiasDao;
    private Article currentArticle; // Almacena la referencia al artículo actual para facilitar el acceso
    // Campos a nivel de clase para almacenar datos del Intent
    private String autor, titulo, descripcion, url, imagenUrl, publicadoEn, contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_detalles);

        FloatingActionButton fabBack = findViewById(R.id.fab_back2);
        fabBack.setImageResource(R.drawable.volver);
        fabBack.setOnClickListener(view -> onBackPressed());

        imagenNoticiaDetalle = findViewById(R.id.imagenNoticiaDetalle);
        tvAutor = findViewById(R.id.tvAutor);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvURL = findViewById(R.id.tvURL);
        tvPublicadoEn = findViewById(R.id.tvPublicadoEn);
        tvContenido = findViewById(R.id.tvContenido);
        btnAccionNoticia = findViewById(R.id.btnGuardarNoticia);

        NoticiasDataBase db = NoticiasDataBase.getDatabase(this);
        noticiasDao = db.noticiasDao();

        Intent intent = getIntent();
        cargarDatosDesdeIntent(intent);
        verificarNoticiaExistente(url);
    }

    private void cargarDatosDesdeIntent(Intent intent) {
        autor = intent.getStringExtra("autor");
        titulo = intent.getStringExtra("titulo");
        descripcion = intent.getStringExtra("descripcion");
        url = intent.getStringExtra("url");
        imagenUrl = intent.getStringExtra("imagen_url");
        publicadoEn = intent.getStringExtra("publicadoEn");
        contenido = intent.getStringExtra("contenido");

        tvAutor.setText("Autor: " + autor);
        tvTitulo.setText("Titulo: " + titulo);
        tvDescripcion.setText("Descripción: " + descripcion);
        tvURL.setText("URL: " + url);
        tvPublicadoEn.setText("Fecha de publicación: " + publicadoEn);
        tvContenido.setText("Contenido: " + contenido);

        Picasso.get().load(imagenUrl).into(imagenNoticiaDetalle);
    }

    private void verificarNoticiaExistente(String url) {
        new Thread(() -> {
            currentArticle = noticiasDao.getArticleByUrl(url);
            runOnUiThread(() -> {
                if (currentArticle != null) {
                    btnAccionNoticia.setText("Borrar Noticia");
                    btnAccionNoticia.setOnClickListener(v -> borrarNoticia());
                } else {
                    btnAccionNoticia.setText("Guardar Noticia");
                    btnAccionNoticia.setOnClickListener(v -> guardarNoticia());
                }
            });
        }).start();
    }

    private void guardarNoticia() {
        new Thread(() -> {
            if (currentArticle == null) {
                currentArticle = new Article();
                currentArticle.setAuthor(autor);
                currentArticle.setTitle(titulo);
                currentArticle.setDescription(descripcion);
                currentArticle.setUrl(url);
                currentArticle.setUrlToImage(imagenUrl);
                currentArticle.setPublishedAt(publicadoEn);
                currentArticle.setContent(contenido);
                noticiasDao.insertArticle(currentArticle);
            }
            runOnUiThread(() -> Toast.makeText(NoticiaDetallesActivity.this, "Noticia guardada con éxito.", Toast.LENGTH_SHORT).show());
        }).start();
    }

    private void borrarNoticia() {
        new Thread(() -> {
            noticiasDao.deleteArticle(currentArticle);
            runOnUiThread(() -> {
                Toast.makeText(NoticiaDetallesActivity.this, "Noticia borrada con éxito.", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}
