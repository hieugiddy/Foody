package com.app.foody.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.foody.R;

public class ActivityChonHinhBinhLuan extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chonhinh_binhluan);
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
            //
            //
    }  }
}
