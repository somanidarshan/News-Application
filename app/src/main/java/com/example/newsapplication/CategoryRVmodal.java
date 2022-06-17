package com.example.newsapplication;


public class CategoryRVmodal {

    private String category;
    private String categoryImgUrl;

    public CategoryRVmodal(String category, String categoryImgUrl) {
        this.category = category;
        this.categoryImgUrl = categoryImgUrl;
    }

    public String getCategory() {
        return category;
    }
    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }
}

