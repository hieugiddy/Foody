package com.app.foody.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.Adapters.CategoryAdapter;
import com.app.foody.Controller.Interfaces.ThanhVienInterfaces;
import com.app.foody.Model.Category;
import com.app.foody.Model.ThanhVienModel;
import com.app.foody.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hbb20.CountryCodePicker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class EditProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Upload ảnh
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;

    // kêt thúc upload ảnh

    TextView tvDate, tvPhoneNumber, tvEmail,editMK,pf_boqua,pf_luu;
    Spinner spnGender;
    private FirebaseAuth mAuth;
    DatePickerDialog.OnDateSetListener setListener;
    private CategoryAdapter categoryAdapter;
    ImageView img_avata;
    EditText inputName;
    String sodt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        mAuth = FirebaseAuth.getInstance();

        spnGender = findViewById(R.id.spn_grender);
        img_avata=(ImageView) findViewById(R.id.img_avata) ;
        pf_boqua=findViewById(R.id.pf_boqua);
        pf_luu=findViewById(R.id.pf_luu);
        editMK= findViewById(R.id.editMK);
        categoryAdapter = new CategoryAdapter(this, R.layout.item_selected, getListCategory());
        spnGender.setAdapter(categoryAdapter);


        tvPhoneNumber = findViewById(R.id.tv_phoneNumber);
        Intent bnphone = getIntent();
        String sdt = bnphone.getStringExtra("phonenumber");
        tvPhoneNumber.setText(sdt);

        tvEmail = findViewById(R.id.tv_email);
        Intent bnemail = getIntent();
        String email = bnemail.getStringExtra("email");
        tvEmail.setText(email);

        tvDate = findViewById(R.id.tv_ngaysinh);
        getInfo();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month= calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        //Code for textview select gender
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditProfile.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(new Color().TRANSPARENT));
                datePickerDialog.show();
            }
        });
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoiEmail();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                tvDate.setText(date);
            }
        };

        tvPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoiSdt();
            }
        });
        editMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoiMK();
            }
        });
        pf_boqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, TrangChu.class);
                startActivity(intent);
                Animatoo.animateShrink(EditProfile.this);
                finish();
            }
        });
        pf_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Luu();
            }
        });
        img_avata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonAnh();
            }
        });

    }
    //upload ảnh
    private void chonAnh() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Lựa chọn hình ảnh"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img_avata.setImageBitmap(bitmap);
                uploadImage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("thanhvien/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final FirebaseUser currentUser =mAuth.getCurrentUser();
                            DatabaseReference dataNodeThanhVien= FirebaseDatabase.getInstance().getReference().child("thanhviens").child(currentUser.getUid()).child("hinhanh");
                            progressDialog.dismiss();
                            String fileName=taskSnapshot.getMetadata().getName().toString();
                            dataNodeThanhVien.setValue(fileName);
                            Toast.makeText(EditProfile.this, "Đã cập nhập", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditProfile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    //kết thúc upload ảnh

    public List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("Nam"));
        list.add(new Category("Nữ"));
        list.add(new Category("Khác"));
        return list;
    }
    public void getInfo(){
        final FirebaseUser currentUser =mAuth.getCurrentUser();
        ThanhVienModel thanhVienModel=new ThanhVienModel();
        thanhVienModel.getThanhVien(currentUser.getUid(), new ThanhVienInterfaces() {
            @Override
            public void getThongTInThanhVienModel(ThanhVienModel thanhVienModel) {
                sodt=thanhVienModel.getSodt();
                String so1 = sodt.substring(0, sodt.length()-3); //ham lay chuoi tu vi tri dau tien den vi tri muon dung
                String so2 = "*";
                //lap lai chuoi so2 n lan (voi n la cac so bi an)
                so2 = new String(new char[sodt.length()-3]).replace("\0", so2);
                //thay the n ki tu dau muon an = n ki tu *
                String sodtNew = sodt.replace(so1, so2);

                String stEmail = currentUser.getEmail();

                inputName=findViewById(R.id.inputName);
                tvPhoneNumber.setText(sodtNew);
                tvEmail.setText(stEmail);
                tvDate.setText(thanhVienModel.getNgaysinh());
                inputName.setText(thanhVienModel.getHoten());

                for(int i = 0; i < getListCategory().size(); i++)
                {
                    if (getListCategory().get(i).getName().equals(thanhVienModel.getGioitinh()))
                    {
                        spnGender.setSelection(i);
                        break;
                    }
                }
                StorageReference storageHinhAnh  = FirebaseStorage.getInstance().getReference().child("thanhvien").child(thanhVienModel.getHinhanh());
                final long ONE_MEGABYTE=1024*1024;
                storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        img_avata=(ImageView) findViewById(R.id.img_avata) ;
                        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        img_avata.setImageBitmap(bitmap);
                    }
                });

            }
        });
    }
    public void DoiMK() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.chang_password, null);
        final EditText ed_password = (EditText) alertLayout.findViewById(R.id.ed_password);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Đổi mật khẩu");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newPassword = ed_password.getText().toString();
                if(newPassword.length()>=8) {
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(EditProfile.this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                                        mAuth.signOut();
                                        Intent intent = new Intent(EditProfile.this, DangNhap.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                        Toast.makeText(EditProfile.this,"Đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(EditProfile.this,"Mật khẩu phải có ít nhất 8 ký tự",Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void DoiSdt() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.edit_phone_number, null);
        final CountryCodePicker ccp = (CountryCodePicker) alertLayout.findViewById(R.id.ccp);
        final EditText edPhoneNumber = (EditText) alertLayout.findViewById(R.id.ed_numberphone);
        final ImageView imgCheck = (ImageView) alertLayout.findViewById(R.id.img_valid);
        final Boolean[] check = {false};
        ccp.registerCarrierNumberEditText(edPhoneNumber);
        ccp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                if(isValidNumber){
                    imgCheck.setImageResource(R.drawable.ic_valid);
                    check[0] = true;
                }else {
                    imgCheck.setImageResource(R.drawable.ic_invalid);
                    check[0] = false;
                }
            }
        });
        edPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString().trim();
                if(input.length() > 0){
                    imgCheck.setVisibility(View.VISIBLE);
                }else {
                    imgCheck.setVisibility(View.GONE);
                }
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Đổi số điện thoại");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (check[0] == true){
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String phone = edPhoneNumber.getText().toString().trim();
                    String uid=user.getUid();
                    DatabaseReference dataNodeThanhVien= FirebaseDatabase.getInstance().getReference().child("thanhviens").child(uid).child("sodt");
                    dataNodeThanhVien.setValue(phone);
                    tvPhoneNumber.setText(phone);
                    Toast.makeText(EditProfile.this,"Đổi số điện thoại thành công",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditProfile.this, "Số điện thoại không chính xác!!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void DoiEmail() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.edit_email, null);
        final EditText ed_emailNew = (EditText) alertLayout.findViewById(R.id.ed_emailNew);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thay đổi email");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String newEmail= ed_emailNew.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                user.updateEmail(newEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(EditProfile.this,"Đổi Email thành công",Toast.LENGTH_SHORT).show();
                                    tvEmail.setText(newEmail);
                                }
                                else
                                    Toast.makeText(EditProfile.this,"Địa chỉ Email không hợp lệ",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void Luu(){
        final FirebaseUser currentUser =mAuth.getCurrentUser();
        DatabaseReference thanhVienNode= FirebaseDatabase.getInstance().getReference().child("thanhviens").child(currentUser.getUid());

        thanhVienNode.child("hoten").setValue(inputName.getText().toString());
        thanhVienNode.child("ngaysinh").setValue(tvDate.getText().toString());
        thanhVienNode.child("gioitinh").setValue(getListCategory().get((int) spnGender.getSelectedItemId()).getName());

        final Dialog dialog= new Dialog(EditProfile.this,R.style.PauseDialog);
        dialog.setContentView(R.layout.success_dialog);

        MaterialButton btnOK=dialog.findViewById(R.id.ok_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent=new Intent(EditProfile.this, TrangChu.class);
                startActivity(intent);
                Animatoo.animateShrink(EditProfile.this);
                finish();
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = String.valueOf(parent.getItemIdAtPosition(position));
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
