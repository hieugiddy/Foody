package com.app.foody.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuanAnModel implements Parcelable, Comparable<QuanAnModel> {
    boolean giaohang;
    String  giodongcua,giomocua,tenquanan,videogioithieu,maquanan;
    List<String> tienich;
    List<String> hinhanhquanan;
    List<BinhLuanModel> binhLuanModelList;
    List<ChiNhanhQuanAn> chiNhanhQuanAnList;
    List<Bitmap> bitmaps;
long giatoida,giatoithieu;
    protected QuanAnModel(Parcel in) {
        giaohang = in.readByte() != 0;
        giodongcua = in.readString();
        giomocua = in.readString();
        tenquanan = in.readString();
        videogioithieu = in.readString();
        maquanan = in.readString();
        tienich = in.createStringArrayList();
        hinhanhquanan = in.createStringArrayList();
        //bitmaps = in.createTypedArrayList(Bitmap.CREATOR);
        luotthich = in.readLong();
        giatoida = in.readLong();
        giatoithieu = in.readLong();
        chiNhanhQuanAnList = new ArrayList<ChiNhanhQuanAn>();
        in.readTypedList(chiNhanhQuanAnList, ChiNhanhQuanAn.CREATOR);

        binhLuanModelList = new ArrayList<BinhLuanModel>();
        in.readTypedList(binhLuanModelList, BinhLuanModel.CREATOR);
    }

    public static final Creator<QuanAnModel> CREATOR = new Creator<QuanAnModel>() {
        @Override
        public QuanAnModel createFromParcel(Parcel in) {
            return new QuanAnModel(in);
        }

        @Override
        public QuanAnModel[] newArray(int size) {
            return new QuanAnModel[size];
        }
    };

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

    public long getGiatoida() {
        return giatoida;
    }

    public void setGiatoida(long giatoida) {
        this.giatoida = giatoida;
    }

    public long getGiatoithieu() {
        return giatoithieu;
    }

    public void setGiatoithieu(long giatoithieu) {
        this.giatoithieu = giatoithieu;
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
                LayDanhSachQuanAn(dataRoot,odauInterface,viTriHienTai,soItemTiepTheo,soItemDaCo);
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

    public void getDanhSachQuanAn(final OdauInterfaces odauInterface, final Location viTriHienTai) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot=dataSnapshot;
                LayDanhSachQuanAn(dataRoot,odauInterface,viTriHienTai,30,0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
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
            loadQuanAn(i, dataSnapshot, odauInterface, viTriHienTai);
        }
    }

    public void loadQuanAn(DataSnapshot i,DataSnapshot dataSnapshot,final OdauInterfaces odauInterface, final Location viTriHienTai) {
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


    @Override
    public int compareTo(QuanAnModel o) {
        ChiNhanhQuanAn chiNhanhQuanAn1=o.getChiNhanhQuanAnList().get(0);
        for(ChiNhanhQuanAn i: o.getChiNhanhQuanAnList()){
            if(chiNhanhQuanAn1.getKhoangCach() > i.getKhoangCach()){
                chiNhanhQuanAn1=i;
            }
        }

        ChiNhanhQuanAn chiNhanhQuanAn2=this.getChiNhanhQuanAnList().get(0);
        for(ChiNhanhQuanAn i: o.getChiNhanhQuanAnList()){
            if(chiNhanhQuanAn2.getKhoangCach() > i.getKhoangCach()){
                chiNhanhQuanAn2=i;
            }
        }
        return (int) (chiNhanhQuanAn2.getKhoangCach()-chiNhanhQuanAn1.getKhoangCach());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (giaohang ? 1 : 0));
        dest.writeString(giodongcua);
        dest.writeString(giomocua);
        dest.writeString(tenquanan);
        dest.writeString(videogioithieu);
        dest.writeString(maquanan);
        dest.writeStringList(tienich);
        dest.writeStringList(hinhanhquanan);
        //dest.writeTypedList(bitmaps);
        dest.writeLong(luotthich);
        dest.writeLong(giatoida);
        dest.writeLong(giatoithieu);
        dest.writeTypedList(chiNhanhQuanAnList);
        dest.writeTypedList(binhLuanModelList);
    }
}
