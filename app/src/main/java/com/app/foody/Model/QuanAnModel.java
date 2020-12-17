package com.app.foody.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    boolean giaohang;
    String  giodongcua,giomocua,tenquanan,videogioithieu,maquanan;
    List<String> tienich;
    List<String> hinhanhquanan;
    List<BinhLuanModel> binhLuanModelList;
    List<ChiNhanhQuanAn> chiNhanhQuanAnList;
    List<Bitmap> bitmaps;
    public List<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    public DataSnapshot getDataRoot() {
        return dataRoot;
    }

    public void setDataRoot(DataSnapshot dataRoot) {
        this.dataRoot = dataRoot;
    }


    public List<ChiNhanhQuanAn> getChiNhanhQuanAnList() {
        return chiNhanhQuanAnList;
    }

    public void setChiNhanhQuanAnList(List<ChiNhanhQuanAn> chiNhanhQuanAnList) {
        this.chiNhanhQuanAnList = chiNhanhQuanAnList;
    }
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

    DataSnapshot dataRoot;
    public void getDanhSachQuanAn(final OdauInterfaces odauInterface, final Location viTriHienTai, final int soItemTiepTheo, final int soItemDaCo) {
       ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            dataRoot=dataSnapshot;
            LayDanhSachQuanAn(dataSnapshot,odauInterface,viTriHienTai,soItemTiepTheo,soItemDaCo);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
       if(dataRoot!=null)
           LayDanhSachQuanAn(dataRoot,odauInterface,viTriHienTai,soItemTiepTheo,soItemDaCo);
       else
           nodeRoot.addListenerForSingleValueEvent(valueEventListener);
}

public void LayDanhSachQuanAn(DataSnapshot dataSnapshot,final OdauInterfaces odauInterface, final Location viTriHienTai,int soItemTiepTheo,int soItemDaCo){
    int k=0;
       //lấy danh sách quán ăn
    for (DataSnapshot i:dataSnapshot.child("quanans").getChildren()){
        if(k==soItemTiepTheo){
            break;
        }
        if(k<soItemDaCo) {
            k++;
            continue;
        }
        k++;
        final QuanAnModel quanAnModel=i.getValue(QuanAnModel.class);
        quanAnModel.setMaquanan(i.getKey());
        // lấy danh sách hình ảnh của một quán ăn theo mã
        List<String> hinhanhlist=new ArrayList<>();
        for (DataSnapshot a:dataSnapshot.child("hinhanhquanans").child(i.getKey()).getChildren()){
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
        //chi nhánh quán ăn
        DataSnapshot dataSnapshotChiNhanhQuanAn=dataSnapshot.child("chinhanhquanans").child(quanAnModel.getMaquanan());
        List<ChiNhanhQuanAn> chiNhanhQuanAns=new ArrayList<>();
        for(DataSnapshot valueChiNhanhQuanAn: dataSnapshotChiNhanhQuanAn.getChildren()){
            ChiNhanhQuanAn chiNhanhQuanAn=valueChiNhanhQuanAn.getValue(ChiNhanhQuanAn.class);
            Location viTriQuanAn=new Location("");
            viTriQuanAn.setLatitude(chiNhanhQuanAn.getLatitude());
            viTriQuanAn.setLongitude(chiNhanhQuanAn.getLongitude());
            Float khoangCach=viTriHienTai.distanceTo(viTriQuanAn);
            chiNhanhQuanAn.setKhoangCach(khoangCach/1000);
            chiNhanhQuanAns.add(chiNhanhQuanAn);
        }
        quanAnModel.setChiNhanhQuanAnList(chiNhanhQuanAns);

        // tải hình
        final List<Bitmap> bitmaps=new ArrayList<>();
        StorageReference storageHinhAnh  = FirebaseStorage.getInstance().getReference().child("hinhquanan").child(quanAnModel.getHinhanhquanan().get(0));
        final long ONE_MEGABYTE=1024*1024;
        storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                bitmaps.add(bitmap);
                quanAnModel.setBitmaps(bitmaps);
                odauInterface.getDanhSachQuanAnModel(quanAnModel);
            }
        });
    }
}


}
