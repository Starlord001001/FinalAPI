package com.example.finalapi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticiasViewHolder extends RecyclerView.ViewHolder {
    ImageView imagenNoticia;
    TextView tvTituloNoticia;

    public NoticiasViewHolder(@NonNull View itemView) {
        super(itemView);
        imagenNoticia = itemView.findViewById(R.id.imagenNoticia);
        tvTituloNoticia = itemView.findViewById(R.id.tvTituloNoticia);
    }
}

