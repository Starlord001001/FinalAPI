package com.example.finalapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        //db.close();
        return newRowId != -1;
    }
}
