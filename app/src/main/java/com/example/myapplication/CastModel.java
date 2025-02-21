package com.example.myapplication;

public class CastModel {
    String cast;
    String castImg;

    public CastModel() {
    }

    public CastModel(String cast, String castImg) {
        this.cast = cast;
        this.castImg = castImg;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCastImg() {
        return castImg;
    }

    public void setCastImg(String castImg) {
        this.castImg = castImg;
    }
}
