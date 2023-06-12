package com.example.dell.myapplication.module;

import java.util.List;

public class VideoClass {
    private int imgId;
    private String type;
    private String url;
    public VideoClass( String type,int imgId) {
        this.imgId = imgId;
        this.type = type;
    }
    public VideoClass( String type,int imgId,String url) {
        this.imgId = imgId;
        this.type = type;
        this.url = url;
    }
    public String getType() {
        return type;
    }
    public int getImgId() {
        return imgId;
    }
}
