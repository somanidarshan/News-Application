package com.example.newsapplication;

public class Articles {
    private String title;
    private String description;
    private String urlToImage;
    private String url;
    private String content;
    private Source source;
    private String publishedAt;

    public Articles(String title, String description, String urlToImage, String url, String content, Source source,String publishedAt) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;
        this.source = source;
        this.publishedAt = publishedAt;
    }

    public String getMpublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Source getSource() {
        return source;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }
}

