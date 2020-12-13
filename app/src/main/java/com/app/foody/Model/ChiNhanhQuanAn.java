package com.app.foody.Model;

import com.app.foody.View.ChiTietQuanAn;

public class ChiNhanhQuanAn {
    String diachi;
    Double latitude,longitude;
    Float khoangCach;

    public ChiNhanhQuanAn(){}

    public String getDiaChi() {
        return diachi;
    }

    public void setDiaChi(String diaChi) {
        this.diachi = diaChi;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Float getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(Float khoangCach) {
        this.khoangCach = khoangCach;
    }
}
