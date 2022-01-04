package com.ppam.icafe.model;

public class MainModel {

    public int judul;
    private String mIdBook;
    private String mJudul;
    private String mHarga;
    private String mDeskripsi;
    private int mImageResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public MainModel(String idBook, String menu, String harga, String deskripsi , int imageResourceId) {
        mIdBook = idBook;
        mJudul = menu;
        mHarga = harga;
        mDeskripsi = deskripsi;
        mImageResourceId = imageResourceId;
    }

    public String getIdBook() {
        return mIdBook;
    }

    public String getJudul() {
        return mJudul;
    }

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public String getHarga(){
        return mHarga;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}