package com.app.foody.View;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.R;

public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener {
        TextView txtdiachi,txttenquan;
        ImageView imagechonhinh;
    ImageView chitiet_back;
final int Request_chonhinh=15;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);
        String tenquan=getIntent().getStringExtra("tenquan");
        String diachi=getIntent().getStringExtra("diachi");
        txtdiachi=findViewById(R.id.txtdiachi);
        txttenquan=findViewById(R.id.txttenquan);
        chitiet_back=findViewById(R.id.chitiet_back);
        imagechonhinh=findViewById(R.id.imagechonhinh);
        txttenquan.setText(tenquan);
        txtdiachi.setText(diachi);
        imagechonhinh.setOnClickListener(this);
        chitiet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.imagechonhinh:
                Intent intenChonhinhbinhluan=new Intent(this,ActivityChonHinhBinhLuan.class);
                startActivityForResult(intenChonhinhbinhluan,Request_chonhinh);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
