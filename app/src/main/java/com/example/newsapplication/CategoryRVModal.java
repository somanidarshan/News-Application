package com.example.newsapplication;

public class CategoryRVModal {
    private  String category;
    private  String categoryImageUrl;

    public CategoryRVModal(String catergory, String categoryImageUrl) {
        this.category = catergory;
        this.categoryImageUrl = categoryImageUrl;
    }



    public String getCatrgory() {
        return category;
    }

    public void setCatrgory(String catrgory) {
        this.category = category;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}

