package com.app.foody.Model;

import java.util.List;

public class BinhLuanModel {
    Double chamdiem;
    long luotthich;
    ThanhVienModel thanhVienModel;
    String noidung,tieude,mauser;
    List<String> hinhanhBinhLuanlist;

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
}
