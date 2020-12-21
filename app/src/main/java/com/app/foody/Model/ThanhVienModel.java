package com.app.foody.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.app.foody.Controller.Interfaces.ThanhVienInterfaces;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThanhVienModel implements Parcelable {

    String hoten,hinhanh,mathanhvien,gioitinh,ngaysinh,sodt;
      private   DatabaseReference dataNodeThanhVien;

      public ThanhVienModel(){
        dataNodeThanhVien= FirebaseDatabase.getInstance().getReference().child("thanhviens");
    }

    protected ThanhVienModel(Parcel in) {
        hoten = in.readString();
        hinhanh = in.readString();
        mathanhvien = in.readString();
        gioitinh = in.readString();
        ngaysinh = in.readString();
        sodt = in.readString();
    }

    public static final Creator<ThanhVienModel> CREATOR = new Creator<ThanhVienModel>() {
        @Override
        public ThanhVienModel createFromParcel(Parcel in) {
            return new ThanhVienModel(in);
        }

        @Override
        public ThanhVienModel[] newArray(int size) {
            return new ThanhVienModel[size];
        }
    };

    public String getMathanhvien() {
        return mathanhvien;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public void setMathanhvien(String mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public  void  ThemThongTinThanhVien(ThanhVienModel thanhVienModel,String uid){
        dataNodeThanhVien.child(uid).setValue(thanhVienModel);
    }
    public void getThanhVien(String uid, final ThanhVienInterfaces thanhVienInterfaces){

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ThanhVienModel thanhVienModel= snapshot.getValue(ThanhVienModel.class);
                thanhVienInterfaces.getThongTInThanhVienModel(thanhVienModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        dataNodeThanhVien.child(uid).addListenerForSingleValueEvent(valueEventListener);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hoten);
        dest.writeString(hinhanh);
        dest.writeString(mathanhvien);
        dest.writeString(gioitinh);
        dest.writeString(ngaysinh);
        dest.writeString(sodt);
    }
}
