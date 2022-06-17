package com.example.newsapplication;

import java.util.ArrayList;

public class NewsModal {
    private int totalResults;
    private String status;
    private ArrayList<Articles> articles;

    public NewsModal(int totalResults, String status, ArrayList<Articles> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

}