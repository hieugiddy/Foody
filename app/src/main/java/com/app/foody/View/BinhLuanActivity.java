package com.app.foody.View;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.AdapterHienThiHinhBinhLuan;
import com.app.foody.Controller.BinhLuanController;
import com.app.foody.Model.BinhLuanModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.List;

public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener {
        TextView txtdiachi,txttenquan,txtDangBinhLuan;
        ImageView imagechonhinh;
        EditText edtieude,ednoidung;
    ImageView chitiet_back;
    RecyclerView recyclerViewbinhluan,recyclerview_chonhinh;
    AdapterHienThiHinhBinhLuan adapterHienThiBinhLuan;
    final int Request_chonhinh=11;
    String maquanan;
    SharedPreferences sharedPreferences;
    BinhLuanController binhLuanController;
    List<String> listHinhDuocChon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);
         maquanan=getIntent().getStringExtra("maquanan");
        String tenquan=getIntent().getStringExtra("tenquan");
        String diachi=getIntent().getStringExtra("diachi");
        txtdiachi=findViewById(R.id.txtdiachi);
        txttenquan=findViewById(R.id.txttenquan);
        edtieude=findViewById(R.id.edtieude);
        ednoidung=findViewById(R.id.ednoidung);
        binhLuanController=new BinhLuanController();
        listHinhDuocChon=new ArrayList<>();
        sharedPreferences=getSharedPreferences("luudangnhap",MODE_PRIVATE);

        chitiet_back=findViewById(R.id.chitiet_back);
        imagechonhinh=findViewById(R.id.imagechonhinh);
        txtDangBinhLuan=findViewById(R.id.DangBinhLuan);
        recyclerViewbinhluan=findViewById(R.id.recylerviewchonbinhluan);
        recyclerview_chonhinh=findViewById(R.id.recyclerview_chonhinh);
     RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerview_chonhinh.setLayoutManager(layoutManager);
        txttenquan.setText(tenquan);
        txtdiachi.setText(diachi);
        imagechonhinh.setOnClickListener(this);
        chitiet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        txtDangBinhLuan.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.imagechonhinh:
                Intent intenChonhinhbinhluan=new Intent(this,ActivityChonHinhBinhLuan.class);
                startActivityForResult(intenChonhinhbinhluan,Request_chonhinh);
                break;
            case R.id.DangBinhLuan:
                BinhLuanModel binhLuanModel=new BinhLuanModel();
                String tieude=edtieude.getText().toString();
                String noidung=ednoidung.getText().toString();
                String mauser=sharedPreferences.getString("mauser","");
                binhLuanModel.setTieude(tieude);
                binhLuanModel.setNoidung(noidung);
                binhLuanModel.setChamdiem(0);
                binhLuanModel.setLuotthich(0);
                binhLuanModel.setMauser(mauser);

                binhLuanController.ThemBinhluan(maquanan,binhLuanModel,listHinhDuocChon);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Request_chonhinh) {
            if (resultCode == RESULT_OK){

                 listHinhDuocChon=data.getStringArrayListExtra("listHinhDuocChon");
            adapterHienThiBinhLuan = new AdapterHienThiHinhBinhLuan(this, R.layout.custom_layouthienthibinhluan_duocchon, listHinhDuocChon);

          recyclerview_chonhinh.setAdapter(adapterHienThiBinhLuan);
           adapterHienThiBinhLuan.notifyDataSetChanged();
        }
        }
    }
}
