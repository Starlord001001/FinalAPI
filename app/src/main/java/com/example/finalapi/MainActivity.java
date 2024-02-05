package com.example.finalapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText idiomaedt;
    private EditText ordenaredt;
    private EditText palabraedt;
    private EditText paginasedt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idiomaedt = findViewById(R.id.editTextLanguage);
        ordenaredt = findViewById(R.id.editTextSortBy);
        palabraedt = findViewById(R.id.editTextKeyword);
        paginasedt = findViewById(R.id.editTextPageSize);

    }

    public void onSearchNewsClick(View view) {
        String q = palabraedt.getText().toString();
        String pageSize = paginasedt.getText().toString();
        String language = idiomaedt.getText().toString();
        String sortBy = ordenaredt.getText().toString();


        Intent intent = new Intent(MainActivity.this, NoticiasActivity.class);
        intent.putExtra("q", q);
        intent.putExtra("pageSize", pageSize);
        intent.putExtra("language", language);
        intent.putExtra("sortBy", sortBy);

        startActivity(intent);
    }
}
