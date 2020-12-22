package com.app.foody.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.Controller.DangKyController;
import com.app.foody.Controller.DownloadImageTask;
import com.app.foody.Controller.Interfaces.ThanhVienInterfaces;
import com.app.foody.Model.ThanhVienModel;
import com.app.foody.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class DangNhap extends AppCompatActivity implements FirebaseAuth.AuthStateListener {
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signGoogle;
    TextView txtDangKy,txtQuenMK;
    EditText emailDN,matKhauDN;
    SharedPreferences sharedPreferences;
    Button btDangNhap;
    private static final int RC_SIGN_IN = 007;
    LoginButton signFB;
    CallbackManager mCallbackManager = CallbackManager.Factory.create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        mAuth = FirebaseAuth.getInstance();

        //
        sharedPreferences=getSharedPreferences("luudangnhap",MODE_PRIVATE);

        //


        txtDangKy=(TextView) findViewById(R.id.tv_DKMoi);
        txtQuenMK=(TextView) findViewById(R.id.tv_QMK);
        emailDN=(EditText) findViewById(R.id.emailDN);
        matKhauDN=(EditText) findViewById(R.id.matKhauDN);
        btDangNhap=(Button) findViewById(R.id.btDangNhap);

        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailDN.getText().toString().isEmpty() || matKhauDN.getText().toString().isEmpty())
                    Toast.makeText(DangNhap.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                else
                    Login(emailDN.getText().toString(), matKhauDN.getText().toString());
            }
        });
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
        txtQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangNhap.this, QuenMk.class);
                startActivity(intent);
            }
        });

        signGoogle=(SignInButton) findViewById(R.id.loginGoogle);
        signFB=(LoginButton) findViewById(R.id.facebook);
        signFB.setReadPermissions("email", "public_profile");
        signFB.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String tokenID=loginResult.getAccessToken().getToken();
                AuthCredential authCredential= FacebookAuthProvider.getCredential(tokenID);
                mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        addMemberIntoDatabase(task.getResult().getUser().getUid());
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent iTrangChu=new Intent(DangNhap.this, TrangChu.class);
                        startActivity(iTrangChu);
                        finish();
                    }
                });

            }

            @Override
            public void onCancel() {
                Toast.makeText(DangNhap.this, "Đăng nhập bị từ chối", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(DangNhap.this, "Đăng nhập lỗi", Toast.LENGTH_SHORT).show();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    private void addMemberIntoDatabase(final String uid){
        ThanhVienModel thanhVienModel=new ThanhVienModel();
        thanhVienModel.getThanhVien(uid, new ThanhVienInterfaces() {
            @Override
            public void getThongTInThanhVienModel(ThanhVienModel thanhVienModel) {
                if(thanhVienModel==null) {
                    ThanhVienModel thanhVienModel1=new ThanhVienModel();
                    FirebaseUser user = mAuth.getCurrentUser();
                    thanhVienModel1.setHinhanh("user.png");
                    thanhVienModel1.setHoten(user.getDisplayName());
                    thanhVienModel1.setGioitinh("Nam");
                    thanhVienModel1.setNgaysinh("1/1/2000");
                    thanhVienModel1.setSodt("*******");

                    thanhVienModel1.setMathanhvien(uid);
                    DangKyController dangKyController = new DangKyController();
                    dangKyController.ThemThongTinThanhVienController(thanhVienModel1, uid);

                    new DownloadImageTask()
                            .execute(user.getPhotoUrl().toString());
                }
            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);

    }

    void Login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent iTrangChu=new Intent(DangNhap.this, TrangChu.class);
                            startActivity(iTrangChu);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Trạng thái", "signInWithEmail:failure", task.getException());
                            Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không hợp lệ",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        String TAG="Log";
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            addMemberIntoDatabase(task.getResult().getUser().getUid());
                            Toast.makeText(DangNhap.this, "Đăng nhập thành công với "+task.getResult().getUser().getEmail(), Toast.LENGTH_SHORT).show();
                            Intent iTrangChu=new Intent(DangNhap.this, TrangChu.class);

                            startActivity(iTrangChu);
                            finish();
                        } else {
                            Toast.makeText(DangNhap.this, "Đăng nhập bằng Google lỗi", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser =mAuth.getCurrentUser();
        mAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser =mAuth.getCurrentUser();
        if(currentUser!=null) {
//
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("mauser",currentUser.getUid());
            editor.commit();
            //
            Toast.makeText(DangNhap.this, "Đã đăng nhập với " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
            Intent iTrangChu=new Intent(DangNhap.this, TrangChu.class);
            startActivity(iTrangChu);
            finish();
        }
    }

}