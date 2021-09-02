package com.example.orderapp;

public class Model {


    String mburgerName;
    String mburgerDetail;
    int mburgerPhoto;

    public Model(String mburgerName, String mburgerDetail, int mburgerPhoto) {
        this.mburgerName = mburgerName;
        this.mburgerDetail = mburgerDetail;
        this.mburgerPhoto = mburgerPhoto;
    }




    public String getmburgerName() {
        return mburgerName;
    }

    public String getmburgerDetail() {
        return mburgerDetail;
    }

    public int getmburgerPhoto() {
        return mburgerPhoto;
    }
}
