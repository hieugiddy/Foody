package com.app.foody.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BinhLuanModel implements Parcelable {
    Double chamdiem;
    long luotthich;
    ThanhVienModel thanhVienModel;
    String noidung,tieude,mauser;
    List<String> hinhanhBinhLuanlist;

    protected BinhLuanModel(Parcel in) {
        chamdiem = in.readDouble();
        luotthich = in.readLong();
        noidung = in.readString();
        tieude = in.readString();
        mauser = in.readString();
        hinhanhBinhLuanlist = in.createStringArrayList();
        mabinhluan = in.readString();
        thanhVienModel = in.readParcelable(ThanhVienModel.class.getClassLoader());
    }

    public static final Creator<BinhLuanModel> CREATOR = new Creator<BinhLuanModel>() {
        @Override
        public BinhLuanModel createFromParcel(Parcel in) {
            return new BinhLuanModel(in);
        }

        @Override
        public BinhLuanModel[] newArray(int size) {
            return new BinhLuanModel[size];
        }
    };

    public List<String> getHinhanhBinhLuanlist() {
        return hinhanhBinhLuanlist;
    }

    public void setHinhanhBinhLuanlist(List<String> hinhanhBinhLuanlist) {
        this.hinhanhBinhLuanlist = hinhanhBinhLuanlist;
    }

    String mabinhluan;

    public String getMabinhluan() {
        return mabinhluan;
    }

    public void setMabinhluan(String mabinhluan) {
        this.mabinhluan = mabinhluan;
    }



    public String getMauser() {
        return mauser;
    }

    public void setMauser(String mauser) {
        this.mauser = mauser;
    }



    public BinhLuanModel()
    {

    }
    public double getChamdiem() {
        return chamdiem;
    }

    public void setChamdiem(double chamdiem) {
        this.chamdiem = chamdiem;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public ThanhVienModel getThanhVienModel() {
        return thanhVienModel;
    }

    public void setThanhVienModel(ThanhVienModel thanhVienModel) {
        this.thanhVienModel = thanhVienModel;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(chamdiem);
        dest.writeLong(luotthich);
        dest.writeString(noidung);
        dest.writeString(tieude);
        dest.writeString(mauser);
        dest.writeStringList(hinhanhBinhLuanlist);
        dest.writeString(mabinhluan);
        dest.writeParcelable(thanhVienModel, flags);
    }
}
