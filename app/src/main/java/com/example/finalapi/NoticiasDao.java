package com.example.finalapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoticiasDao {
    private NoticiasDbHelper dbHelper;

    public NoticiasDao(Context context) {
        dbHelper = new NoticiasDbHelper(context);
    }

    public boolean insertarNoticia(String titulo, String autor, String descripcion, String url, String imagenUrl, String publicadoEn, String contenido) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NoticiasContract.NoticiaEntry.COLUMN_NAME_TITULO, titulo);
        values.put(NoticiasContract.NoticiaEntry.COLUMN_NAME_AUTOR, autor);
        values.put(NoticiasContract.NoticiaEntry.COLUMN_NAME_DESCRIPCION, descripcion);
        values.put(NoticiasContract.NoticiaEntry.COLUMN_NAME_URL, url);
        values.put(NoticiasContract.NoticiaEntry.COLUMN_NAME_IMAGEN_URL, imagenUrl);
        values.put(NoticiasContract.NoticiaEntry.COLUMN_NAME_PUBLICADO_EN, publicadoEn);
        values.put(NoticiasContract.NoticiaEntry.COLUMN_NAME_CONTENIDO, contenido);

        long newRowId = db.insert(NoticiasContract.NoticiaEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId != -1;
    }

    public List<Article> obtenerNoticiasGuardadas() {
        List<Article> listaNoticias = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                NoticiasContract.NoticiaEntry._ID,
                NoticiasContract.NoticiaEntry.COLUMN_NAME_TITULO,
                NoticiasContract.NoticiaEntry.COLUMN_NAME_AUTOR,
                NoticiasContract.NoticiaEntry.COLUMN_NAME_DESCRIPCION,
                NoticiasContract.NoticiaEntry.COLUMN_NAME_URL,
                NoticiasContract.NoticiaEntry.COLUMN_NAME_IMAGEN_URL,
                NoticiasContract.NoticiaEntry.COLUMN_NAME_PUBLICADO_EN,
                NoticiasContract.NoticiaEntry.COLUMN_NAME_CONTENIDO,
        };

        Cursor cursor = db.query(
                NoticiasContract.NoticiaEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Article article = new Article();
            article.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NoticiasContract.NoticiaEntry.COLUMN_NAME_TITULO)));
            article.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(NoticiasContract.NoticiaEntry.COLUMN_NAME_AUTOR)));
            article.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(NoticiasContract.NoticiaEntry.COLUMN_NAME_DESCRIPCION)));
            article.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(NoticiasContract.NoticiaEntry.COLUMN_NAME_URL)));
            article.setUrlToImage(cursor.getString(cursor.getColumnIndexOrThrow(NoticiasContract.NoticiaEntry.COLUMN_NAME_IMAGEN_URL)));
            article.setPublishedAt(cursor.getString(cursor.getColumnIndexOrThrow(NoticiasContract.NoticiaEntry.COLUMN_NAME_PUBLICADO_EN)));
            article.setContent(cursor.getString(cursor.getColumnIndexOrThrow(NoticiasContract.NoticiaEntry.COLUMN_NAME_CONTENIDO)));

            listaNoticias.add(article);
        }
        cursor.close();
        db.close();

        return listaNoticias;
    }
}
