package com.app.foody.View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.AdapterChonhinhBinhluan;
import com.app.foody.Model.ChonhinhBinhluanModel;
import com.app.foody.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityChonHinhBinhLuan extends AppCompatActivity implements View.OnClickListener {
    List<ChonhinhBinhluanModel> listduongdan;
    List<String> listhinhduocchon;
    RecyclerView recyclerViewChonHinhBinhLuan;
    AdapterChonhinhBinhluan adapterChonhinhBinhluan;
    TextView txtxong;
    ImageView txtcamera;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chonhinh_binhluan);

        txtxong=findViewById(R.id.txtxong);

        txtxong.setOnClickListener(this);

        listduongdan=new ArrayList<>();
        listhinhduocchon=new ArrayList<>();

        recyclerViewChonHinhBinhLuan=findViewById(R.id.recylerviewchonbinhluan);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        adapterChonhinhBinhluan=new AdapterChonhinhBinhluan(this,R.layout.custom_chonhinh_binhluan,listduongdan);
        recyclerViewChonHinhBinhLuan.setLayoutManager(layoutManager);
        recyclerViewChonHinhBinhLuan.setAdapter(adapterChonhinhBinhluan);

       int checkread= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
       if (checkread!= PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
       }else {
           gettatcahinh();
       }



        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                gettatcahinh();
            }
        }
    }

    public  void gettatcahinh(){
        String [] projection={MediaStore.Images.Media.DATA};
        Uri uri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor=getContentResolver().query(uri,projection,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String duongdan=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            ChonhinhBinhluanModel chonhinhBinhluanModel=new ChonhinhBinhluanModel(duongdan,false);
            listduongdan.add(chonhinhBinhluanModel);
            adapterChonhinhBinhluan.notifyDataSetChanged();
            cursor.moveToNext();
            //
    }  }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.txtxong:
            for (ChonhinhBinhluanModel value:listduongdan){
                if (value.isIscheck()){
                    listhinhduocchon.add(value.getDuongdan());
                } }
                Intent data=getIntent();
            data.putStringArrayListExtra("listHinhDuocChon",(ArrayList<String>) listhinhduocchon);
            setResult(RESULT_OK,data);
            finish();
                break;
        }
    }
}
