package com.example.newsadda;

class News {
    private String title;
    private String imageUrl;
    private String authors;
    private String URL;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public News(String title, String imageUrl, String authors, String URL, String time) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.authors = authors;
        this.URL = URL;
        this.time=time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
