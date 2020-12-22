package com.app.foody.View;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.Model.MonAnModel;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.Model.ThemThucDonModel;
import com.app.foody.Model.ThucDonModel;
import com.app.foody.Model.TienIchModel;
import com.app.foody.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.view.View.GONE;

public class Themquanan extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    final int RESULT_IMG1=111;
    final int RESULT_IMG2=112;
    final int RESULT_IMG3=113;
    final int RESULT_IMG4=114;
    final int RESULT_IMG5=115;
    final int RESULT_IMG6=116;
    final int RESULT_IMGTHUCDON=117;
    final int RESULT_IMGVIDEO=118;

    LinearLayout khungTienIch, khungChiNhanh, khungChuaChiNhanh, khungChucThucDon;
    Spinner spinnerKhuVucs;
    ImageView imageTam;
    ImageView imgHinhQuan1,imgHinhQuan2,imgHinhQuan3,imgHinhQuan4,imgHinhQuan5,imgHinhQuan6, imgVideo;
    VideoView videoView;
    Button btgiomocua, btgiodongcua, btThemQuanAn;
    String gioMoCua, gioDongCua, khuvuc;
    RadioGroup rdTrangThai;
    EditText edTenQuanAn, edGiaToiDa, edGiaToiThieu;

    List<ThucDonModel> thucDonModels;
    List<String> tienichList;
    List<String> chiNhanhList;
    List<String> khuVucList, thucdonList;
    List<ThemThucDonModel> themThucDonModelList;
    List<Bitmap> hinhDaChup;
    List<Uri> hinhQuanAn;
    String videoSelected;

    ArrayAdapter<String> adapterKhuVucs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themquanan);
        Start();
        thucDonModels = new ArrayList<>();
        khuVucList = new ArrayList<>();
        thucdonList = new ArrayList<>();
        tienichList = new ArrayList<>();
        chiNhanhList = new ArrayList<>();
        hinhDaChup = new ArrayList<>();
        hinhQuanAn = new ArrayList<>();
        themThucDonModelList = new ArrayList<>();
        adapterKhuVucs = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,khuVucList);
        spinnerKhuVucs.setAdapter(adapterKhuVucs);
        adapterKhuVucs.notifyDataSetChanged();

        CloneChiNhanh();
        CloneThucDon();
        LayDanhSachKhuVuc();
        LayDanhSachTienich();


        btgiomocua.setOnClickListener(this);
        btgiodongcua.setOnClickListener(this);
        spinnerKhuVucs.setOnItemSelectedListener(this);
        imgHinhQuan1.setOnClickListener(this);
        imgHinhQuan2.setOnClickListener(this);
        imgHinhQuan3.setOnClickListener(this);
        imgHinhQuan4.setOnClickListener(this);
        imgHinhQuan5.setOnClickListener(this);
        imgHinhQuan6.setOnClickListener(this);
        imgVideo.setOnClickListener(this);
        btThemQuanAn.setOnClickListener(this);
    }
    public void Start(){
        spinnerKhuVucs = (Spinner)findViewById(R.id.khuvucs);
        btgiodongcua = (Button)findViewById(R.id.giodongcua);
        btgiomocua = (Button)findViewById(R.id.giomocua);
        khungTienIch = (LinearLayout)findViewById(R.id.khungTienIch);
        khungChiNhanh = (LinearLayout)findViewById(R.id.khungChiNhanh);
        khungChuaChiNhanh = (LinearLayout)findViewById(R.id.khungChuaChiNhanh);
        khungChucThucDon = (LinearLayout)findViewById(R.id.khungChucThucDon);
        imgHinhQuan1 = (ImageView) findViewById(R.id.imgHinhQuan1);
        imgHinhQuan2 = (ImageView) findViewById(R.id.imgHinhQuan2);
        imgHinhQuan3 = (ImageView) findViewById(R.id.imgHinhQuan3);
        imgHinhQuan4 = (ImageView) findViewById(R.id.imgHinhQuan4);
        imgHinhQuan5 = (ImageView) findViewById(R.id.imgHinhQuan5);
        imgHinhQuan6 = (ImageView) findViewById(R.id.imgHinhQuan6);
        videoView = (VideoView) findViewById(R.id.videoView);
        imgVideo = (ImageView)findViewById(R.id.imgVideo);
        btThemQuanAn = (Button)findViewById(R.id.btThemQuanAn);
        rdTrangThai = (RadioGroup)findViewById(R.id.rdgTrangThai);
        edGiaToiDa = (EditText)findViewById(R.id.edGiaToiDa);
        edGiaToiThieu = (EditText)findViewById(R.id.edGiaToiThieu);
        edTenQuanAn = (EditText)findViewById(R.id.edTenQuanAn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case RESULT_IMG1:
                if(RESULT_OK == resultCode){
                    Uri imageUri = data.getData();
                    imgHinhQuan1.setImageURI(imageUri);
                    hinhQuanAn.add(imageUri);
                };break;
            case RESULT_IMG2:
                if(RESULT_OK == resultCode){
                    Uri imageUri = data.getData();
                    imgHinhQuan2.setImageURI(imageUri);
                    hinhQuanAn.add(imageUri);
                };break;
            case RESULT_IMG3:
                if(RESULT_OK == resultCode){
                    Uri imageUri = data.getData();
                    imgHinhQuan3.setImageURI(imageUri);
                    hinhQuanAn.add(imageUri);
                };break;
            case RESULT_IMG4:
                if(RESULT_OK == resultCode){
                    Uri imageUri = data.getData();
                    imgHinhQuan4.setImageURI(imageUri);
                    hinhQuanAn.add(imageUri);
                };break;
            case RESULT_IMG5:
                if(RESULT_OK == resultCode){
                    Uri imageUri = data.getData();
                    imgHinhQuan5.setImageURI(imageUri);
                    hinhQuanAn.add(imageUri);
                };break;
            case RESULT_IMG6:
                if(RESULT_OK == resultCode){
                    Uri imageUri = data.getData();
                    imgHinhQuan6.setImageURI(imageUri);
                    hinhQuanAn.add(imageUri);
                };break;
            case  RESULT_IMGTHUCDON:
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageTam.setImageBitmap(bitmap);
                hinhDaChup.add(bitmap);break;
            case RESULT_IMGVIDEO:
                if(RESULT_OK == resultCode){
                    imgVideo.setVisibility(View.GONE);
                    Uri imageUri = data.getData();
                    videoSelected = imageUri.toString();
                    videoView.setVideoURI(imageUri);
                    videoView.start();
                };break;
        }
    }

    private void CloneThucDon(){
        final View view = LayoutInflater.from(Themquanan.this).inflate(R.layout.layout_clone_thucdon,null);
        final Spinner spinnerThucDons = (Spinner)view.findViewById(R.id.thucdons);
        Button btThemThucDon = (Button)view.findViewById(R.id.btThemThucDon);
        final Button btXoaThucDon = (Button)view.findViewById(R.id.btXoaThucDon);
        final EditText edTenmon = (EditText) view.findViewById(R.id.edTenMon);
        final EditText edGiatien = (EditText)view.findViewById(R.id.edGiaTien);
        final ImageView imageChupHinh = (ImageView)view.findViewById(R.id.imageChupHinh);
        imageTam = imageChupHinh;

        ArrayAdapter<String> adapterThucDons = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,thucdonList);
        spinnerThucDons.setAdapter(adapterThucDons);
        adapterThucDons.notifyDataSetChanged();
        if(thucdonList.size()==0){
            LayDanhSachThucDon(adapterThucDons);
        }
        imageChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,RESULT_IMGTHUCDON);
            }
        });
        btThemThucDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMon1 = edTenmon.getText().toString();
                String giaTien = edGiatien.getText().toString();
                if(tenMon1.trim().equals("") || giaTien.trim().equals("")|| imageChupHinh.getDrawable() == null){
                    Toast.makeText(Themquanan.this,"Bạn chưa nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    v.setVisibility(view.GONE);
                    String tenMon = edTenmon.getText().toString();
                    btXoaThucDon.setVisibility(view.VISIBLE);
                    btXoaThucDon.setTag(tenMon);
                    String tenHinh = String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg";
                    int position = spinnerThucDons.getSelectedItemPosition();
                    String maThucDon = thucDonModels.get(position).getMathucdon();
                    MonAnModel monAnModel = new MonAnModel();
                    monAnModel.setTenmon(tenMon);
                    monAnModel.setGiatien(Long.parseLong(edGiatien.getText().toString()));
                    monAnModel.setHinhanh(tenHinh);

                    ThemThucDonModel themThucDonModel = new ThemThucDonModel();
                    themThucDonModel.setMathucdon(maThucDon);
                    themThucDonModel.setMonAnModels(monAnModel);
                    themThucDonModelList.add(themThucDonModel);
                    CloneThucDon();
                }
            }
        });
        btXoaThucDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenThucDon = v.getTag().toString();
                thucdonList.remove(tenThucDon);
                khungChucThucDon.removeView(view);
            }
        });
        khungChucThucDon.addView(view);
    }
    private void CloneChiNhanh(){
        final View view = LayoutInflater.from(Themquanan.this).inflate(R.layout.layout_clone_chinhanh,null);
        final ImageButton imageButtonThemChiNhanh = (ImageButton) view.findViewById(R.id.btThemChiNhanh);
        final ImageButton imageButtonXoaChiNhanh = (ImageButton) view.findViewById(R.id.btXoaChiNhanh);
        imageButtonThemChiNhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText chinhanh = (EditText) view.findViewById(R.id.edTenChiNhanh);
                String tenchinhanh = chinhanh.getText().toString();
                v.setVisibility(GONE);
                imageButtonXoaChiNhanh.setVisibility(view.VISIBLE);
                imageButtonXoaChiNhanh.setTag(tenchinhanh);
                chiNhanhList.add(chinhanh.getText().toString());
                CloneChiNhanh();
            }
        });
        imageButtonXoaChiNhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenchinhanh = v.getTag().toString();
                chiNhanhList.remove(tenchinhanh);
                khungChuaChiNhanh.removeView(view);
            }
        });
        khungChuaChiNhanh.addView(view);
    }
    private void LayDanhSachTienich() {
        FirebaseDatabase.getInstance().getReference().child("quanlytienichs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String matienich = dataSnapshot.getKey();
                    final TienIchModel tienichModel = dataSnapshot.getValue(TienIchModel.class);
                    tienichModel.setMatienich(matienich);

                    CheckBox checkBox = new CheckBox(Themquanan.this);
                    checkBox.setButtonTintList(ColorStateList.valueOf(Color.RED));
                    checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    checkBox.setTag(matienich);
                    checkBox.setText(tienichModel.getTentienich());
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            String maTienIch = buttonView.getTag().toString();
                            if(isChecked){
                                tienichList.add(maTienIch);
                            }else{
                                tienichList.remove(maTienIch);
                            }
                            Log.d("kiemtra",""+tienichList.size());
                        }
                    });
                    khungTienIch.addView(checkBox);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void LayDanhSachKhuVuc(){
        FirebaseDatabase.getInstance().getReference().child("khuvucs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String tenKhuVuc = dataSnapshot.getKey();
                    khuVucList.add(tenKhuVuc);
                }
                adapterKhuVucs.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void LayDanhSachThucDon(final ArrayAdapter<String> adapterThucDons){
        FirebaseDatabase.getInstance().getReference().child("thucdons").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ThucDonModel thucDonModel = new ThucDonModel();
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue(String.class);
                    thucDonModel.setMathucdon(key);
                    thucDonModel.setTenthucdon(value);
                    thucDonModels.add(thucDonModel);

                    thucdonList.add(value);
                }
                adapterThucDons.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClick(final View v) {
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        switch (v.getId()) {
            case R.id.giodongcua:
                TimePickerDialog dongCuaIimePickerDialog = new TimePickerDialog(Themquanan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        gioDongCua = hourOfDay + ":"+ minute;
                        ((Button)v).setText(gioDongCua);
                    }
                },gio,phut,true);
                dongCuaIimePickerDialog.show();
                break;
            case R.id.giomocua: {
                TimePickerDialog moCuaTimePickerDialog = new TimePickerDialog(Themquanan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        gioMoCua = hourOfDay + ":"+ minute;
                        ((Button)v).setText(gioMoCua);
                    }
                },gio,phut,true);
                moCuaTimePickerDialog.show();
                break;
            }
            case R.id.imgHinhQuan1:
                ChonHinhTuGallary(RESULT_IMG1);
                break;
            case R.id.imgHinhQuan2:
                ChonHinhTuGallary(RESULT_IMG2);
                break;
            case R.id.imgHinhQuan3:
                ChonHinhTuGallary(RESULT_IMG3);
                break;
            case R.id.imgHinhQuan4:
                ChonHinhTuGallary(RESULT_IMG4);
                break;
            case R.id.imgHinhQuan5:
                ChonHinhTuGallary(RESULT_IMG5);
                break;
            case R.id.imgHinhQuan6:
                ChonHinhTuGallary(RESULT_IMG6);
                break;
            case R.id.imgVideo:
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Chọn video..."),RESULT_IMGVIDEO);break;
            case R.id.btThemQuanAn:
                ThemQuanAn();
                break;
        }
    }
    private void ThemQuanAn(){
        String tenQuanAn = edTenQuanAn.getText().toString();
        long giaToiDa = Long.parseLong(edGiaToiDa.getText().toString());
        long giaToiThieu = Long.parseLong(edGiaToiThieu.getText().toString());
        int idRadioSelected = rdTrangThai.getCheckedRadioButtonId();
        boolean giaoHang = false;
        if(idRadioSelected == R.id.rdGiaoHang){
            giaoHang = true;
        }else
            giaoHang = false;
        DatabaseReference nodeRoot = FirebaseDatabase.getInstance().getReference();
        DatabaseReference nodeQuanAn = nodeRoot.child("quanans");
        String maQuanAn = nodeQuanAn.push().getKey();
        nodeRoot.child("khuvucs").child(khuvuc).push().setValue(maQuanAn);
        nodeRoot.child("chinhanhquanans").child(maQuanAn).setValue(chiNhanhList );

        for (String chinhanh:chiNhanhList){
            String urlGeo = "https://maps.googleapis.com/maps/api/geocode/json?address="+chinhanh.replace(" ","%20")+"&key=AIzaSyArDfi6xqaR3tbGkJov0I08y4eYV9KnrSI";
            DownloadToaDo downloadToaDo = new DownloadToaDo();
            downloadToaDo.execute(urlGeo);
        }
        QuanAnModel quanAnModel= new QuanAnModel();

        quanAnModel.setGiaohang(giaoHang);
        quanAnModel.setGiatoida(giaToiDa);
        quanAnModel.setGiatoithieu(giaToiThieu);
        quanAnModel.setGiodongcua(gioDongCua);
        quanAnModel.setGiomocua(gioMoCua);
        quanAnModel.setTenquanan(tenQuanAn);
        quanAnModel.setLuotthich(0);
        quanAnModel.setTienich(tienichList);
        quanAnModel.setVideogioithieu(maQuanAn+".mp4");
        //nodeQuanAn.child("quanans").child(maQuanAn).push().setValue(quanAnModel);
        /*Log.d("kiemtra",""+tenQuanAn+" "+giaToiDa+" "+giaToiThieu+" "+giaoHang+" "+gioDongCua+" "+gioMoCua+" "+maQuanAn+" "+tienichList+" "+quanAnModel.getLuotthich());*/
    }
    class DownloadToaDo extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("kiemtra"," "+s);
        }
    }
    private void ChonHinhTuGallary(int requestcode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Chọn ảnh..."),requestcode);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.khuvucs:
                khuvuc = khuVucList.get(position);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}