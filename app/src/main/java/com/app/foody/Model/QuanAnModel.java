package com.app.foody.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.app.foody.Controller.Interfaces.OdauInterfaces;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    boolean giaohang;
    String  giodongcua,giomocua,tenquanan,videogioithieu,maquanan;
    List<String> tienich;
    List<String> hinhanhquanan;
    List<BinhLuanModel> binhLuanModelList;
    long luotthich;
   DatabaseReference nodeRoot;

   public QuanAnModel(){
        nodeRoot=FirebaseDatabase.getInstance().getReference();
   }

    public List<BinhLuanModel> getBinhLuanModelList() {
        return binhLuanModelList;
    }

    public void setBinhLuanModelList(List<BinhLuanModel> binhLuanModelList) {
        this.binhLuanModelList = binhLuanModelList;
    }

    public List<String> getHinhanhquanan() {
        return hinhanhquanan;
    }

    public void setHinhanhquanan(List<String> hinhanhquanan) {
        this.hinhanhquanan = hinhanhquanan;
    }

    public DatabaseReference getNodeRoot() {
        return nodeRoot;
    }

    public void setNodeRoot(DatabaseReference nodeRoot) {
        this.nodeRoot = nodeRoot;
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

public void getDanhSachQuanAn(final OdauInterfaces odauInterface) {
       ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //lấy danh sách quán ăn
            for (DataSnapshot i:dataSnapshot.child("quanans").getChildren()){
                QuanAnModel quanAnModel=i.getValue(QuanAnModel.class);
                quanAnModel.setMaquanan(i.getKey());
                // lấy danh sách hình ảnh của một quán ăn theo mã
                List<String> hinhanhlist=new ArrayList<>();
                for (DataSnapshot a: dataSnapshot.child("hinhanhquanans").child(i.getKey()).getChildren()){
                    hinhanhlist.add(a.getValue(String.class));
                }
                quanAnModel.setHinhanhquanan(hinhanhlist);
               //
                //lấy danh sách bình luận của quán ăn
                DataSnapshot dataSnapshotBinhLuan= dataSnapshot.child("binhluans").child(quanAnModel.getMaquanan());
                List<BinhLuanModel> binhLuanModels=new ArrayList<>();
                for (DataSnapshot valuebinhluan:dataSnapshotBinhLuan.getChildren()){
                    BinhLuanModel binhLuanModel=valuebinhluan.getValue(BinhLuanModel.class);
                    binhLuanModel.setMabinhluan(valuebinhluan.getKey());
                    ThanhVienModel thanhVienModel=dataSnapshot.child("thanhviens").child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                    binhLuanModel.setThanhVienModel(thanhVienModel);
                    //
                    List<String> hinhanhBinhLuanList=new ArrayList<>();
                     DataSnapshot snapshotNodeHinhAnhBL=  dataSnapshot.child("hinhanhbinhluans").child(binhLuanModel.getMabinhluan());
                    for (DataSnapshot valueHinhAnhBinhLuan:snapshotNodeHinhAnhBL.getChildren()){
                        hinhanhBinhLuanList.add(valueHinhAnhBinhLuan.getValue(String.class));
                    }
                    binhLuanModel.setHinhanhBinhLuanlist(hinhanhBinhLuanList);
                    binhLuanModels.add(binhLuanModel);
                }
                quanAnModel.setBinhLuanModelList(binhLuanModels);
                    //
                odauInterface.getDanhSachQuanAnModel(quanAnModel);
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    nodeRoot.addListenerForSingleValueEvent(valueEventListener);
}


}
