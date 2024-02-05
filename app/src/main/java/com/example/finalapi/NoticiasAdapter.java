package com.example.finalapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder> {

    private List<Article> noticias;
    private Context context;

    // Pass the context to the constructor
    public NoticiasAdapter(Context context) {
        this.context = context;
    }

    public void setNoticias(List<Article> noticias) {
        this.noticias = noticias;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticia, parent, false);
        return new NoticiasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiasViewHolder holder, int position) {
        Article noticia = noticias.get(position);

        // Configurar la vista del elemento de la lista
        holder.tvTituloNoticia.setText(noticia.getTitle());
        Picasso.get().load(noticia.getUrlToImage()).into(holder.imagenNoticia);

        // Handle click event
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use the initialized context
                Intent intent = new Intent(context, NoticiaDetallesActivity.class);
                intent.putExtra("autor", noticia.getAuthor());
                intent.putExtra("titulo", noticia.getTitle());
                intent.putExtra("descripcion", noticia.getDescription());
                intent.putExtra("url", noticia.getUrl());
                intent.putExtra("imagen_url", noticia.getUrlToImage());
                intent.putExtra("publicadoEn", noticia.getPublishedAt());
                intent.putExtra("contenido", noticia.getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticias != null ? noticias.size() : 0;
    }

    public static class NoticiasViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenNoticia;
        TextView tvTituloNoticia;

        public NoticiasViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenNoticia = itemView.findViewById(R.id.imagenNoticia);
            tvTituloNoticia = itemView.findViewById(R.id.tvTituloNoticia);
        }
    }
}

