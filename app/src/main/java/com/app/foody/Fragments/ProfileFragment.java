package com.app.foody.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.foody.Controller.Interfaces.ThanhVienInterfaces;
import com.app.foody.Model.ThanhVienModel;
import com.app.foody.R;
import com.app.foody.View.DangNhap;
import com.app.foody.View.EditProfile;
import com.app.foody.View.SlashScreenActivity;
import com.app.foody.View.Slide;
import com.app.foody.View.TrangChu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class ProfileFragment extends Fragment {
    FirebaseAuth mAuth;
    ImageView avt_pf;
    TextView pf_dangxuat,pf_dangnhap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        pf_dangnhap=v.findViewById(R.id.pf_dangnhap);
        TextView pf_xemhd=v.findViewById(R.id.pf_xemhd);
        avt_pf=v.findViewById(R.id.avt_pf);
        pf_dangxuat=v.findViewById(R.id.pf_dangxuat);

        mAuth= FirebaseAuth.getInstance();
        FirebaseUser currentUser =mAuth.getCurrentUser();

        if(currentUser==null){
            pf_dangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), DangNhap.class);
                    startActivity(intent);
                }
            });
            pf_xemhd.setVisibility(View.INVISIBLE);
            pf_dangxuat.setText("Thoát ứng dụng");
            pf_dangxuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                    System.exit(0);
                }
            });
        }
        else {
            pf_xemhd.setVisibility(View.VISIBLE);
            pf_dangxuat.setText("Đăng xuất");
            getInfo();

            pf_xemhd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), EditProfile.class);
                    startActivity(intent);
                }
            });
            pf_dangxuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    Intent intent=new Intent(getActivity(), SlashScreenActivity.class);
                    startActivity(intent);
                }
            });
        }

        return v;
    }
    public void getInfo(){
        final FirebaseUser currentUser =mAuth.getCurrentUser();
        ThanhVienModel thanhVienModel=new ThanhVienModel();
        thanhVienModel.getThanhVien(currentUser.getUid(), new ThanhVienInterfaces() {
            @Override
            public void getThongTInThanhVienModel(ThanhVienModel thanhVienModel) {
                pf_dangnhap.setText(thanhVienModel.getHoten());
                StorageReference storageHinhAnh  = FirebaseStorage.getInstance().getReference().child("thanhvien").child(thanhVienModel.getHinhanh());
                final long ONE_MEGABYTE=1024*1024;
                storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        avt_pf.setImageBitmap(bitmap);
                    }
                });

            }
        });
    }
}