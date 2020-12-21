package com.app.foody.Model;

public class ChonhinhBinhluanModel {
    String duongdan;
    boolean ischeck;

    public ChonhinhBinhluanModel(String duongdan, boolean ischeck) {
        this.duongdan = duongdan;
        this.ischeck = ischeck;
    }

    public String getDuongdan() {
        return duongdan;
    }

    public void setDuongdan(String duongdan) {
        this.duongdan = duongdan;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}
