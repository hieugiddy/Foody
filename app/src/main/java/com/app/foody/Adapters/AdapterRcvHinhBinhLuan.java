package com.app.foody.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.BinhLuanModel;
import com.app.foody.R;
import com.app.foody.View.HienThiChiTietBinhLuanActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AdapterRcvHinhBinhLuan extends RecyclerView.Adapter<AdapterRcvHinhBinhLuan.RcvHinhBinhLuanHolder>{
    Context context;
    int resource;
    List<Bitmap> listHinh;
    BinhLuanModel binhLuanModel;
    boolean isChiTietBnhLuan;

    public AdapterRcvHinhBinhLuan(Context context, int resource, List<Bitmap> listHinh, BinhLuanModel binhLuanModel, boolean isChiTietBnhLuan){
        this.context = context;
        this.resource = resource;
        this.listHinh = listHinh;
        this.binhLuanModel = binhLuanModel;
        this.isChiTietBnhLuan = isChiTietBnhLuan;
    }

    public class RcvHinhBinhLuanHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhBinhLuan;
        TextView tvSoHinhBL;
        FrameLayout khungSoHinhBL;

        public RcvHinhBinhLuanHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhBinhLuan = itemView.findViewById(R.id.img_hinh_binhluan);
            tvSoHinhBL = itemView.findViewById(R.id.tv_sohinh_binhluan);
            khungSoHinhBL = itemView.findViewById(R.id.khungSoHinhBL);
        }
    }

    @NonNull
    @Override
    public RcvHinhBinhLuanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new AdapterRcvHinhBinhLuan.RcvHinhBinhLuanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RcvHinhBinhLuanHolder holder, final int position) {
        holder.imgHinhBinhLuan.setImageBitmap(listHinh.get(position));
        //Kiểm tra nếu là hình thứ 4 thì sẽ làm các công việc bên trong
        if (!isChiTietBnhLuan){
            if (position ==3){

                int sohinhconlai = listHinh.size() -4;
                if (sohinhconlai > 0){
                    holder.khungSoHinhBL.setVisibility(View.VISIBLE);
                    holder.tvSoHinhBL.setText("+" + sohinhconlai);
                    holder.imgHinhBinhLuan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent iChiTietBinhLuan = new Intent(context, HienThiChiTietBinhLuanActivity.class);
                            iChiTietBinhLuan.putExtra("binhluanmodel", binhLuanModel);
                            context.startActivity(iChiTietBinhLuan);
                        }
                    });
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        if (!isChiTietBnhLuan){
            if (listHinh.size()<4){
              return listHinh.size();
            }else {
                return 4;
            }

        }else {
            return listHinh.size();
        }

    }

}
