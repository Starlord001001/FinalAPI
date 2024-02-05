package com.example.finalapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NoticiaDetallesActivity extends AppCompatActivity {

    private ImageView imagenNoticiaDetalle;
    private TextView tvAutor, tvTitulo, tvDescripcion, tvURL, tvPublicadoEn, tvContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_detalles);

        // Initialize views
        imagenNoticiaDetalle = findViewById(R.id.imagenNoticiaDetalle);
        tvAutor = findViewById(R.id.tvAutor);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvURL = findViewById(R.id.tvURL);
        tvPublicadoEn = findViewById(R.id.tvPublicadoEn);
        tvContenido = findViewById(R.id.tvContenido);

        Intent intent = getIntent();
        if (intent != null) {
            String autor = intent.getStringExtra("autor");
            String titulo = intent.getStringExtra("titulo");
            String descripcion = intent.getStringExtra("descripcion");
            String url = intent.getStringExtra("url");
            String imagen_url = intent.getStringExtra("imagen_url");
            String publicadoEn = intent.getStringExtra("publicadoEn");
            String contenido = intent.getStringExtra("contenido");

            tvAutor.setText("Autor: " + autor);
            tvTitulo.setText("Titulo: " + titulo);
            tvDescripcion.setText("Descripción: " + descripcion);
            tvURL.setText("URL: " + url);
            tvPublicadoEn.setText("Fecha de publicación: " + publicadoEn);
            tvContenido.setText("Contenido: " + contenido);

            Picasso.get().load(imagen_url).into(imagenNoticiaDetalle);
        }
    }
}
