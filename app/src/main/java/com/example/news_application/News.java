package com.example.news_application;

public class News {
    String title;
    String author;
    String url;
    String urlToImage;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public News(String title, String author, String url, String urlToImage) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.urlToImage = urlToImage;
    }


}
