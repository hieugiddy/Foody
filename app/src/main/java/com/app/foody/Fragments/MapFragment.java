package com.app.foody.Fragments;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.app.foody.Model.ChiNhanhQuanAn;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MapFragment extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    Double latitude,longitude;
    SharedPreferences sharedPreferences;
    QuanAnModel quanAnModel;
    TextView txtDiaChiQuanAn,txtgioihangia,txtTenQuan;
    ImageView imgQuanAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);
        SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        txtDiaChiQuanAn=findViewById(R.id.txtDiaChiQuanAn);
        txtgioihangia=findViewById(R.id.txtgioihangia);
        imgQuanAn=findViewById(R.id.imgQuanAn);
        txtTenQuan=findViewById(R.id.txtTenQuan);
        map=googleMap;
        quanAnModel = getIntent().getParcelableExtra("quanan");
        ChiNhanhQuanAn chiNhanhQuanAn=quanAnModel.getChiNhanhQuanAnList().get(0);
        for(ChiNhanhQuanAn i: quanAnModel.getChiNhanhQuanAnList()){
            if(chiNhanhQuanAn.getKhoangCach() > i.getKhoangCach()){
                chiNhanhQuanAn=i;
            }
        }
        txtDiaChiQuanAn.setText(chiNhanhQuanAn.getDiaChi());

        StorageReference storageHinhAnhQuan = FirebaseStorage.getInstance().getReference().child("hinhquanan").child(quanAnModel.getHinhanhquanan().get(0));
        final long ONE_MEGABYTE=1024*1024;
        storageHinhAnhQuan.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgQuanAn.setImageBitmap(bitmap);
            }
        });

        txtTenQuan.setText(quanAnModel.getTenquanan());
        txtgioihangia.setText(quanAnModel.getGiatoithieu()+"-"+quanAnModel.getGiatoida());
        latitude=chiNhanhQuanAn.getLatitude();
        longitude=chiNhanhQuanAn.getLongitude();
        LatLng current=new LatLng(latitude,longitude);
        map.addMarker(new MarkerOptions().position(current).title(chiNhanhQuanAn.getDiaChi()));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(current,18));
    }
}
