package com.app.foody.Fragments;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

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

public class MapFragment extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    Double latitude,longitude;
    SharedPreferences sharedPreferences;
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
        map=googleMap;
        sharedPreferences=getApplicationContext().getSharedPreferences("toado",MODE_PRIVATE);
        latitude=Double.parseDouble(sharedPreferences.getString("latitude","0"));
        longitude=Double.parseDouble(sharedPreferences.getString("longitude","0"));
        LatLng current=new LatLng(latitude,longitude);
        map.addMarker(new MarkerOptions().position(current).title("48 Cao Thắng"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(current,18));
    }
}
