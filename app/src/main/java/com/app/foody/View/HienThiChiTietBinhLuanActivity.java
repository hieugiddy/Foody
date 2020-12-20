package com.app.foody.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import com.app.foody.Adapters.AdapterRcvHinhBinhLuan;
import com.app.foody.Model.BinhLuanModel;
import com.app.foody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HienThiChiTietBinhLuanActivity extends AppCompatActivity {
    CircleImageView crcImgBinhLuan;
    TextView tvTieuDeBL, tvNoiDungBL, tvChamDiemBL;
    RecyclerView rcvListAnhBinhLuan;
    List<Bitmap> bitmapList;
    BinhLuanModel binhLuanModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sử dụng lại vì có cấu trúc giống nhau
        setContentView(R.layout.custom_layout_binhluan);

        crcImgBinhLuan = findViewById(R.id.crc_img_binhluan);
        tvTieuDeBL = findViewById(R.id.tv_tieude_binhluan);
        tvNoiDungBL = findViewById(R.id.tv_noidung_binhluan);
        tvChamDiemBL = findViewById(R.id.tv_chamdiem_binhluan);
        rcvListAnhBinhLuan = findViewById(R.id.rcv_list_anh_binhluan);

        bitmapList = new ArrayList<>();

        binhLuanModel = getIntent().getParcelableExtra("binhluanmodel");

        tvTieuDeBL.setText(binhLuanModel.getTieude());
        tvNoiDungBL.setText(binhLuanModel.getNoidung());
        tvChamDiemBL.setText(binhLuanModel.getChamdiem()+"");
        setAvataNguoiBinhLuan(crcImgBinhLuan, binhLuanModel.getThanhVienModel().getHinhanh());

        // truyền vào 1 list hình ảnh của 1 bình luận
        for (String linkhinh : binhLuanModel.getHinhanhBinhLuanlist()){
            StorageReference storageHinhuser= FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
            final long ONE_MEGABYTE=1024*1024;
            storageHinhuser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    bitmapList.add(bitmap);
                    //Kiểm tra list bitmap này đã load đủ số ảnh hay chưa
                    if (bitmapList.size() == binhLuanModel.getHinhanhBinhLuanlist().size()){
                        //Nạp adapter cho RecyclerView List Hình ảnh bình luận
                        AdapterRcvHinhBinhLuan adapterRcvHinhBinhLuan = new AdapterRcvHinhBinhLuan(HienThiChiTietBinhLuanActivity.this, R.layout.custom_layout_hinhbinhluan,
                                bitmapList, binhLuanModel, true);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HienThiChiTietBinhLuanActivity.this, 2);
                        rcvListAnhBinhLuan.setLayoutManager(layoutManager);
                        rcvListAnhBinhLuan.setAdapter(adapterRcvHinhBinhLuan);
                        adapterRcvHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private  void setAvataNguoiBinhLuan(final CircleImageView circleImageView , String linkhinh){
        StorageReference storageHinhuser= FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);
        final long ONE_MEGABYTE=1024*1024;
        storageHinhuser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });

    }
}