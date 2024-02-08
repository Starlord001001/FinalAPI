package com.example.finalapi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface NoticiasDAO {

    @Insert
    void insertArticle(Article article);

    @Query("SELECT * FROM Article")
    List<Article> getAllArticles();

    @Query("SELECT * FROM Article WHERE url = :url LIMIT 1")
    Article getArticleByUrl(String url);

    @Delete
    void deleteArticle(Article article);
}
