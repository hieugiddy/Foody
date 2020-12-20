package com.app.foody.Model;

public class GanToiListViewBean_GiaoNhanh {
    int anh;
    String monan, gia, quangduong, thoigian;

    public GanToiListViewBean_GiaoNhanh(){

    }
    public GanToiListViewBean_GiaoNhanh(int anh, String monan, String gia, String quangduong, String thoigian) {
        super();
        this.anh = anh;
        this.monan = monan;
        this.gia = gia;
        this.quangduong = quangduong;
        this.thoigian = thoigian;
    }


    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }
    public String getMonan() {
        return monan;
    }

    public void setMonan(String monan) {
        this.monan = monan;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getQuangduong() {
        return quangduong;
    }

    public void setQuangduong(String quangduong) {
        this.quangduong = quangduong;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}

