package com.example.finalapi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article {


    @PrimaryKey(autoGenerate = true)
    private int articleId;
    private int sourceId;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article() {
    }
    public String getAuthor() {
        return author;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getUrl() {
        return url;
    }


    public String getUrlToImage() {
        return urlToImage;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    public String getContent() {
        return content;
    }

    public int getArticleId() {return articleId;}

    public int getSourceId() {return sourceId;}

    public void setSourceId(int sourceId) {this.sourceId = sourceId;}
    public void setArticleId(int articleId) {this.articleId = articleId;}

    public void setAuthor(String author) {this.author = author;}

    public void setTitle(String title) {this.title = title;}

    public void setDescription(String description) {this.description = description;}

    public void setUrl(String url) {this.url = url;}

    public void setUrlToImage(String urlToImage) {this.urlToImage = urlToImage;}

    public void setPublishedAt(String publishedAt) {this.publishedAt = publishedAt;}

    public void setContent(String content) {this.content = content;}
}
