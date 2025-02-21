package com.example.myapplication;

public class FeatureModel  {
    private String cover,title,thumb,desc,cast,movie,genre;

    public FeatureModel() {
    }

    public FeatureModel(String cover, String title, String thumb, String desc,String cast,String genre,String movie) {
        this.cover = cover;
        this.title = title;
        this.thumb = thumb;
        this.desc = desc;
        this.cast = cast;
        this.movie=movie;
        this.genre=genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
