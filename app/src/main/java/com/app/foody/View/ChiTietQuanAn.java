package com.app.foody.View;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.AdapterBinhLuan;
import com.app.foody.Adapters.DuocDatNhieuAdapter;
import com.app.foody.Controller.ThucDonController;
import com.app.foody.Fragments.MapFragment;
import com.app.foody.Model.DuocDatNhieuItem;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.Model.TienIchModel;
import com.app.foody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAn extends AppCompatActivity implements View.OnClickListener {
    ListView lv;
    DuocDatNhieuAdapter adapter;
    ArrayList<DuocDatNhieuItem> arr_bean;
    ImageView chitiet_back, imgHinhAnhQuan,imgPlayTrailer;
    TextView tvTenQuanAn, tvThoiGianHoatDong, tvTrangThaiHoatDong, tvDiaChiQuanAn, tvTongSoHinhAnh,tv_binhluan1,
            tvTongSoCheckIn, tvTongSoBinhLuan, tvTongSoLuuLai, tvTenquananToolBar, tvTongSoBinhLuan1,txtgioihangia;
    QuanAnModel quanAnModel;
    Toolbar toolbarODau;
    RecyclerView rcvListBinhLuan,recyclerThucDon;
    AdapterBinhLuan binhLuanAdapter;
    LinearLayout khungTienIch;
    VideoView videoView;
    ThucDonController thucDonController;
    View map;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietquanan);

        quanAnModel = getIntent().getParcelableExtra("quanan");

        chitiet_back=findViewById(R.id.chitiet_back);
        imgHinhAnhQuan = findViewById(R.id.img_hinh_anh_quan);
        tvTenQuanAn = findViewById(R.id.tv_ten_quanan);
        tvTenquananToolBar = findViewById(R.id.tv_ten_quanan_toolbar);
        tvThoiGianHoatDong = findViewById(R.id.tv_thoigian_hoat_dong);
        tvTrangThaiHoatDong = findViewById(R.id.tv_trang_thai_hoatdong);
        tvDiaChiQuanAn = findViewById(R.id.tv_dia_chi_quan);
        txtgioihangia = findViewById(R.id.gioihangia);
        map=findViewById(R.id.mapView);

        khungTienIch = findViewById(R.id.khungtienich);

        tvTongSoHinhAnh = findViewById(R.id.tv_tongso_hinhanh);
        tvTongSoBinhLuan = findViewById(R.id.tv_tongso_binhluan);
        tvTongSoCheckIn = findViewById(R.id.tv_tongso_checkin);
        tvTongSoLuuLai = findViewById(R.id.tv_tongso_luulai);
        tvTongSoBinhLuan1 = findViewById(R.id.tv_tongso_binhluan1);
        videoView = (VideoView) findViewById(R.id.videoTrailer);
        imgPlayTrailer = (ImageView) findViewById(R.id.imgPlayTrailer);
        recyclerThucDon = (RecyclerView) findViewById(R.id.recyclerThucDon);


        thucDonController = new ThucDonController();
        tv_binhluan1= findViewById(R.id.tv_binhluan);
        tv_binhluan1.setOnClickListener(this);


        rcvListBinhLuan = findViewById(R.id.rcv_list_binhluan_chitietquanan);

        //Log.d("kiemtra", quanAnModel.getTenquanan());

        chitiet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChiTietQuanAn.this, MapFragment.class);
                i.putExtra("quanan", quanAnModel);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String giohientai = dateFormat.format(calendar.getTime());
        String giomocua = quanAnModel.getGiomocua();
        String giodongcua = quanAnModel.getGiodongcua();
        //Log.d("kiemtra", giohientai + "");

        Resources res = getResources();
        try {
            Date datehientai = dateFormat.parse(giohientai);
            Date dategiomocua = dateFormat.parse(giomocua);
            Date dategiodongcua = dateFormat.parse(giodongcua);

            //Log.d("kiemtra", datehientai.after(dategiomocua)  +"");
            if (datehientai.after(dategiomocua) && datehientai.before(dategiodongcua) ){

                tvTrangThaiHoatDong.setText("Đang mở cửa");
                tvTrangThaiHoatDong.setTextColor(res.getColor(R.color.green));
            }else{
                tvTrangThaiHoatDong.setText("Đã đóng cửa");
                tvTrangThaiHoatDong.setTextColor(res.getColor(R.color.colorRed));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTenQuanAn.setText(quanAnModel.getTenquanan());
        tvTenquananToolBar.setText(quanAnModel.getTenquanan());
        tvDiaChiQuanAn.setText(quanAnModel.getChiNhanhQuanAnList().get(0).getDiaChi());
        tvThoiGianHoatDong.setText(quanAnModel.getGiomocua() + " - " +quanAnModel.getGiodongcua());
        tvTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + "");
        tvTongSoBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");
        tvTongSoBinhLuan1.setText(quanAnModel.getBinhLuanModelList().size() + " Bình luận");

        DownloadHinhTienIch(khungTienIch);

        if (quanAnModel.getGiatoida()!=0 && quanAnModel.getGiatoithieu()!=0){
            NumberFormat numberFormat= new DecimalFormat("###,###");
            String giatoithieu=numberFormat.format(quanAnModel.getGiatoithieu())+" đ";
            String giatoida=numberFormat.format(quanAnModel.getGiatoida())+" đ";
            txtgioihangia.setText(giatoithieu+" - "+giatoida);
        }else {
            txtgioihangia.setVisibility(View.INVISIBLE);
        }


        StorageReference storageHinhAnhQuan = FirebaseStorage.getInstance().getReference().child("hinhquanan").child(quanAnModel.getHinhanhquanan().get(0));
        final long ONE_MEGABYTE=1024*1024;
        storageHinhAnhQuan.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgHinhAnhQuan.setImageBitmap(bitmap);
            }
        });
        //Load Video Trailer
        if(quanAnModel.getVideogioithieu() != null){
            imgPlayTrailer.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.VISIBLE);
            imgHinhAnhQuan.setVisibility(View.GONE);
            StorageReference storageVideo = FirebaseStorage.getInstance().getReference().child("video").child(quanAnModel.getVideogioithieu());
            storageVideo.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    videoView.setVideoURI(uri);
                    videoView.seekTo(1);
                }
            });
            imgPlayTrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoView.start();
                    MediaController mediaController = new MediaController(ChiTietQuanAn.this);
                    videoView.setMediaController(mediaController);
                    imgPlayTrailer.setVisibility(View.GONE);
                }
            });

        }else{
            videoView.setVisibility(View.GONE);
            imgPlayTrailer.setVisibility(View.GONE);
            imgHinhAnhQuan.setVisibility(View.VISIBLE);
        }
        //Load danh sách bình luận
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvListBinhLuan.setLayoutManager(layoutManager);

        binhLuanAdapter = new AdapterBinhLuan(this, R.layout.custom_layout_binhluan, quanAnModel.getBinhLuanModelList());
        rcvListBinhLuan.setAdapter(binhLuanAdapter);
        binhLuanAdapter.notifyDataSetChanged();
        thucDonController.getDanhSachThucDonQuanAnTheoMa(this,quanAnModel.getMaquanan(),recyclerThucDon);
    }


    private void DownloadHinhTienIch(final LinearLayout khungtienich){
        for (String matienich:quanAnModel.getTienich()){
            DatabaseReference nodeTienich= FirebaseDatabase.getInstance().getReference().child("quanlytienichs").child(matienich);
            nodeTienich.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    TienIchModel tienIchModel=snapshot.getValue(TienIchModel.class);
                    StorageReference storageHinhAnhQuan = FirebaseStorage.getInstance().getReference().child("hinhtienich").child(tienIchModel.getHinhtienich());
            final long ONE_MEGABYTE=1024*1024;
            storageHinhAnhQuan.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    ImageView imagetienich =new ImageView(ChiTietQuanAn.this);
                    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(50,50);
                    layoutParams.setMargins(10,10,10,10);
                    imagetienich.setScaleType(ImageView.ScaleType.FIT_XY);
                    imagetienich.setPadding(10,5,10,5);
                    imagetienich.setImageBitmap(bitmap);
                  khungtienich.addView(imagetienich);
                }
            });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.tv_binhluan:
                Intent ibinhluan=new Intent(this,BinhLuanActivity.class);
                ibinhluan.putExtra("maquanan",quanAnModel.getMaquanan());
                ibinhluan.putExtra("tenquan",quanAnModel.getTenquanan());
                ibinhluan.putExtra("diachi",quanAnModel.getChiNhanhQuanAnList().get(0).getDiaChi());
                startActivity(ibinhluan);
        }
    }
}
