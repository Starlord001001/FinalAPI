package com.example.finalapi;

import java.util.ArrayList;

public class NewsResponse {

    private String status;
    private int totalResults;
    private ArrayList<Article> articles;

    public NewsResponse() {
        this.articles = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    /*public List<Article> getArticles() {
        return articles;
    }*/

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}

