package com.example.finalapi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoticiasDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Noticias.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoticiasContract.NoticiaEntry.TABLE_NAME + " (" +
                    NoticiasContract.NoticiaEntry._ID + " INTEGER PRIMARY KEY," +
                    NoticiasContract.NoticiaEntry.COLUMN_NAME_TITULO + " TEXT," +
                    NoticiasContract.NoticiaEntry.COLUMN_NAME_AUTOR + " TEXT," +
                    NoticiasContract.NoticiaEntry.COLUMN_NAME_DESCRIPCION + " TEXT," +
                    NoticiasContract.NoticiaEntry.COLUMN_NAME_URL + " TEXT," +
                    NoticiasContract.NoticiaEntry.COLUMN_NAME_IMAGEN_URL + " TEXT," +
                    NoticiasContract.NoticiaEntry.COLUMN_NAME_PUBLICADO_EN + " TEXT," +
                    NoticiasContract.NoticiaEntry.COLUMN_NAME_CONTENIDO + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoticiasContract.NoticiaEntry.TABLE_NAME;

    public NoticiasDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
