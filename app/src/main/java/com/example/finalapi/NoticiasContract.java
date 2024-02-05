package com.example.finalapi;

import android.provider.BaseColumns;

public final class NoticiasContract {

    private NoticiasContract() {}

    public static class NoticiaEntry implements BaseColumns {
        public static final String TABLE_NAME = "noticia";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_AUTOR = "autor";
        public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_IMAGEN_URL = "imagen_url";
        public static final String COLUMN_NAME_PUBLICADO_EN = "publicadoEn";
        public static final String COLUMN_NAME_CONTENIDO = "contenido";
    }
}
