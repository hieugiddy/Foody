package com.app.foody.View;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SlashScreenActivity extends AppCompatActivity {

    TextView txtPhienBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_slashscreen);

        txtPhienBan = findViewById(R.id.txtPhienBan);

        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            txtPhienBan.setText(getString(R.string.version) + " " + packageInfo.versionName);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FirebaseAuth mAuth= FirebaseAuth.getInstance();

                    FirebaseUser currentUser =mAuth.getCurrentUser();
                    if(currentUser==null){
                        Intent intent=new Intent(SlashScreenActivity.this, Slide.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent=new Intent(SlashScreenActivity.this, TrangChu.class);
                        startActivity(intent);
                    }
                    finish();
            }},2000);
        }catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }


    }
}