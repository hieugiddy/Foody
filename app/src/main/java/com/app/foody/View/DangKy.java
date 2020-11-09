package com.app.foody.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.Controller.DangKyController;
import com.app.foody.Model.ThanhVienModel;
import com.app.foody.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangKy extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button dk;
    EditText email,password,rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        dk=(Button) findViewById(R.id.bt_DangKy);
        email=(EditText) findViewById(R.id.ed_EmailDK);
        password=(EditText) findViewById(R.id.ed_PasswordDK);
        rePassword=(EditText) findViewById(R.id.ed_NhapLaiPassword);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || rePassword.getText().toString().isEmpty())
                    Toast.makeText(DangKy.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                else
                    if(password.getText().toString().equals(rePassword.getText().toString())) {
                        if(password.getText().toString().length()>=8) {
                            DangKy(email.getText().toString(), password.getText().toString());
                            dk.setText("Loading...");
                        }
                        else {
                            Toast.makeText(DangKy.this,"Mật khẩu phải có ít nhất 8 ký tự",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(DangKy.this,"Mật khẩu không giống nhau, vui lòng điền lại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void DangKy(String email,String mk){
        mAuth.createUserWithEmailAndPassword(email, mk)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            ThanhVienModel thanhVienModel=new ThanhVienModel();
                            thanhVienModel.setHinhanh("user.png");
                            thanhVienModel.setHoten(user.getEmail());
                            thanhVienModel.setGioitinh("Nữ");
                            thanhVienModel.setNgaysinh("1/1/2000");
                            thanhVienModel.setSodt("0368376081");
                            String uid=task.getResult().getUser().getUid();
                            thanhVienModel.setMathanhvien(uid);
                            DangKyController dangKyController=new DangKyController();
                            dangKyController.ThemThongTinThanhVienController(thanhVienModel,uid);
                            Log.d("trang thai", "Tạo tài khoản thành công");

                            Toast.makeText(DangKy.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            Intent iUpdateProfile=new Intent(DangKy.this,Profile.class);
                            startActivity(iUpdateProfile);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("trạng thái", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(DangKy.this, "Tài khoản đã tồn tại",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}