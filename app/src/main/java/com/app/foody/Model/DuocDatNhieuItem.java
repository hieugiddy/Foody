package com.app.foody.Model;

import android.widget.ImageButton;

public class DuocDatNhieuItem {
    private int image;
    private String title;
    private String theloai;
    private String gia;
    private String hethang;
    private ImageButton themgiohang;

    public DuocDatNhieuItem(int image, String title, String theloai, String gia) {
        this.image = image;
        this.title = title;
        this.theloai = theloai;
        this.gia = gia;
    }




    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getHethang() {
        return hethang;
    }

    public void setHethang(String hethang) {
        this.hethang = hethang;
    }



    public ImageButton getThemgiohang() {
        return themgiohang;
    }

    public void setThemgiohang(ImageButton themgiohang) {
        this.themgiohang = themgiohang;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
