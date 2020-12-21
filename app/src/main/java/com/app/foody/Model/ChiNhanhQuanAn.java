package com.app.foody.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.foody.View.ChiTietQuanAn;

public class ChiNhanhQuanAn implements Parcelable {
    String diachi;
    Double latitude,longitude;
    Float khoangCach;

    public ChiNhanhQuanAn(){}

    protected ChiNhanhQuanAn(Parcel in) {
        diachi = in.readString();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            khoangCach = null;
        } else {
            khoangCach = in.readFloat();
        }
    }

    public static final Creator<ChiNhanhQuanAn> CREATOR = new Creator<ChiNhanhQuanAn>() {
        @Override
        public ChiNhanhQuanAn createFromParcel(Parcel in) {
            return new ChiNhanhQuanAn(in);
        }

        @Override
        public ChiNhanhQuanAn[] newArray(int size) {
            return new ChiNhanhQuanAn[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diachi);
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        if (khoangCach == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(khoangCach);
        }
    }
}
