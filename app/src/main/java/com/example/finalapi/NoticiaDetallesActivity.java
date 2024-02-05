package com.example.finalapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class NoticiaDetallesActivity extends AppCompatActivity {

    private ImageView imagenNoticiaDetalle;
    private TextView tvAutor, tvTitulo, tvDescripcion, tvURL, tvPublicadoEn, tvContenido;
    private Button btnGuardarNoticia;
    private NoticiasDao noticiasDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_detalles);

        imagenNoticiaDetalle = findViewById(R.id.imagenNoticiaDetalle);
        tvAutor = findViewById(R.id.tvAutor);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvURL = findViewById(R.id.tvURL);
        tvPublicadoEn = findViewById(R.id.tvPublicadoEn);
        tvContenido = findViewById(R.id.tvContenido);
        btnGuardarNoticia = findViewById(R.id.btnGuardarNoticia);

        noticiasDao = new NoticiasDao(this);

        Intent intent = getIntent();
        String autor = intent.getStringExtra("autor");
        String titulo = intent.getStringExtra("titulo");
        String descripcion = intent.getStringExtra("descripcion");
        String url = intent.getStringExtra("url");
        String imagenUrl = intent.getStringExtra("imagen_url");
        String publicadoEn = intent.getStringExtra("publicadoEn");
        String contenido = intent.getStringExtra("contenido");

        tvAutor.setText("Autor: " + autor);
        tvTitulo.setText("Titulo: " + titulo);
        tvDescripcion.setText("Descripción: " + descripcion);
        tvURL.setText("URL: " + url);
        tvPublicadoEn.setText("Fecha de publicación: " + publicadoEn);
        tvContenido.setText("Contenido: " + contenido);


        Picasso.get().load(intent.getStringExtra("imagen_url")).into(imagenNoticiaDetalle);

        btnGuardarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = noticiasDao.insertarNoticia(titulo, autor, descripcion, url, imagenUrl, publicadoEn, contenido);
                if (isInserted) {
                    Toast.makeText(NoticiaDetallesActivity.this, "Noticia guardada con éxito.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NoticiaDetallesActivity.this, "Error al guardar la noticia.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
