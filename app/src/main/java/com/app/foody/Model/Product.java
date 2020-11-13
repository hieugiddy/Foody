package com.app.foody.Model;

import androidx.annotation.NonNull;

import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Product {
    List<String> hinhanhquanan;

    String tenquanan,maquanan;
    List<BinhLuanModel> binhLuanModelList;

    public List<BinhLuanModel> getBinhLuanModelList() {
        return binhLuanModelList;
    }

    public void setBinhLuanModelList(List<BinhLuanModel> binhLuanModelList) {
        this.binhLuanModelList = binhLuanModelList;
    }


    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }


    public List<String> getHinhanhquanan() {
        return hinhanhquanan;
    }

    public void setHinhanhquanan(List<String> hinhanhquanan) {
        this.hinhanhquanan = hinhanhquanan;
    }


    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public DatabaseReference getNodeRoot() {
        return nodeRoot;
    }

    public void setNodeRoot(DatabaseReference nodeRoot) {
        this.nodeRoot = nodeRoot;
    }

    DatabaseReference nodeRoot;
    public Product(){
        nodeRoot= FirebaseDatabase.getInstance().getReference();
    }

    public void getDanhSachQuanAn(final OdauInterfaces odauInterface) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //lấy danh sách quán ăn
                for (DataSnapshot i : dataSnapshot.child("quanans").getChildren()) {
                    final Product quanAnModel = i.getValue(Product.class);
                    quanAnModel.setMaquanan(i.getKey());
                    // lấy danh sách hình ảnh của một quán ăn theo mã
                    List<String> hinhanhlist = new ArrayList<>();
                    for (DataSnapshot a : dataSnapshot.child("hinhanhquanans").child(i.getKey()).getChildren()) {
                        hinhanhlist.add(a.getValue(String.class));
                    }
                    quanAnModel.setHinhanhquanan(hinhanhlist);


                    DataSnapshot dataSnapshotBinhLuan= dataSnapshot.child("binhluans").child(quanAnModel.getMaquanan());
                    List<BinhLuanModel> binhLuanModels=new ArrayList<>();
                    for (DataSnapshot valuebinhluan:dataSnapshotBinhLuan.getChildren()) {
                        BinhLuanModel binhLuanModel = valuebinhluan.getValue(BinhLuanModel.class);
                        binhLuanModel.setMabinhluan(valuebinhluan.getKey());
                        ThanhVienModel thanhVienModel = dataSnapshot.child("thanhviens").child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                        binhLuanModel.setThanhVienModel(thanhVienModel);
                    }
                    quanAnModel.setBinhLuanModelList(binhLuanModels);
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