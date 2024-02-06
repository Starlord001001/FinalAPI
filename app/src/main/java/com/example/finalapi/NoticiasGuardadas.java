package com.example.finalapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class NoticiasGuardadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_guardadas);

        FloatingActionButton fabBack = findViewById(R.id.fab_back3);
        fabBack.setImageResource(R.drawable.volver);
        fabBack.setOnClickListener(view -> onBackPressed());




    }
}
