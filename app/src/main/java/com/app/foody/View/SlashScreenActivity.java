package com.app.foody.View;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.app.foody.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SlashScreenActivity extends AppCompatActivity {
    TextView txtPhienBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_slashscreen);

        txtPhienBan = findViewById(R.id.txtPhienBan);


        chuyentrang();
    }
    public void chuyentrang(){
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtPhienBan.setText(getString(R.string.version) + " " + packageInfo.versionName);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();

                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser == null) {
                        Intent intent = new Intent(SlashScreenActivity.this, Slide.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SlashScreenActivity.this, TrangChu.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }, 2000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}