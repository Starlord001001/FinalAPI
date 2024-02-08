package com.example.finalapi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class, Source.class}, version = 1, exportSchema = false)
public abstract class NoticiasDataBase extends RoomDatabase {
    public abstract NoticiasDAO noticiasDao();

    private static volatile NoticiasDataBase INSTANCE;

    static NoticiasDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoticiasDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NoticiasDataBase.class, "noticias_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
