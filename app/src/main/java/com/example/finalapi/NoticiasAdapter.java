package com.example.finalapi;

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
