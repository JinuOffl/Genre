package com.example.myapplication;

public class SearchModel {

    private String cover, title, thumb, desc, cast, movie;

    private String Scover,Stitle,Sthumb,Sdesc,Scast,Smovie;

    public SearchModel() {
    }

    public SearchModel(String cover, String title, String thumb, String desc, String cast, String movie, String scover, String stitle, String sthumb, String sdesc, String scast, String smovie) {
        this.cover = cover;
        this.title = title;
        this.thumb = thumb;
        this.desc = desc;
        this.cast = cast;
        this.movie = movie;
        Scover = scover;
        Stitle = stitle;
        Sthumb = sthumb;
        Sdesc = sdesc;
        Scast = scast;
        Smovie = smovie;
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

    public String getScover() {
        return Scover;
    }

    public void setScover(String scover) {
        Scover = scover;
    }

    public String getStitle() {
        return Stitle;
    }

    public void setStitle(String stitle) {
        Stitle = stitle;
    }

    public String getSthumb() {
        return Sthumb;
    }

    public void setSthumb(String sthumb) {
        Sthumb = sthumb;
    }

    public String getSdesc() {
        return Sdesc;
    }

    public void setSdesc(String sdesc) {
        Sdesc = sdesc;
    }

    public String getScast() {
        return Scast;
    }

    public void setScast(String scast) {
        Scast = scast;
    }

    public String getSmovie() {
        return Smovie;
    }

    public void setSmovie(String smovie) {
        Smovie = smovie;
    }
}
