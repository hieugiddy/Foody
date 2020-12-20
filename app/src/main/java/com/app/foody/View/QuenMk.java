package com.app.foody.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class QuenMk extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email_qmk;
    Button bt_GuiEmailKP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);
        auth = FirebaseAuth.getInstance();
        auth.setLanguageCode("vi");

        email_qmk = (EditText) findViewById(R.id.email_qmk);
        bt_GuiEmailKP = (Button) findViewById(R.id.bt_GuiEmailKP);
        bt_GuiEmailKP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(email_qmk.getText().toString().isEmpty())
                   Toast.makeText(QuenMk.this, "Vui lòng điền địa chỉ email" ,
                           Toast.LENGTH_SHORT).show();
               else
                   FcQuenMK(email_qmk.getText().toString());
            }
        });


    }
    void FcQuenMK(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(QuenMk.this, "Vui lòng kiểm tra hộp thư email",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(QuenMk.this, "Địa chỉ email không hợp lệ",
                                    Toast.LENGTH_SHORT).show();
                    }
                });
    }
}