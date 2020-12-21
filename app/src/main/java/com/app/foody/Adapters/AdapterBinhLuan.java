package com.app.foody.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.BinhLuanModel;
import com.app.foody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterBinhLuan extends RecyclerView.Adapter<AdapterBinhLuan.ViewHolder> {

    Context context;
    int layout;
    List<BinhLuanModel> binhLuanModelList;
    List<Bitmap> bitmapList;

    public AdapterBinhLuan(Context context, int layout, List<BinhLuanModel> binhLuanModelList){
        this.context = context;
        this.layout = layout;
        this.binhLuanModelList = binhLuanModelList;

        bitmapList = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView crcImgBinhLuan;
        TextView tvTieuDeBL, tvNoiDungBL, tvChamDiemBL;
        RecyclerView rcvListAnhBinhLuan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crcImgBinhLuan = itemView.findViewById(R.id.crc_img_binhluan);
            tvTieuDeBL = itemView.findViewById(R.id.tv_tieude_binhluan);
            tvNoiDungBL = itemView.findViewById(R.id.tv_noidung_binhluan);
            tvChamDiemBL = itemView.findViewById(R.id.tv_chamdiem_binhluan);
            rcvListAnhBinhLuan = itemView.findViewById(R.id.rcv_list_anh_binhluan);
        }
    }

    @NonNull
    @Override
    public AdapterBinhLuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBinhLuan.ViewHolder holder, int position) {
        final BinhLuanModel binhLuanModel = binhLuanModelList.get(position);
        holder.tvTieuDeBL.setText(binhLuanModel.getTieude());
        holder.tvNoiDungBL.setText(binhLuanModel.getNoidung());
        holder.tvChamDiemBL.setText(binhLuanModel.getChamdiem()+"");
        setAvataNguoiBinhLuan(holder.crcImgBinhLuan, binhLuanModel.getThanhVienModel().getHinhanh());

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
                        AdapterRcvHinhBinhLuan adapterRcvHinhBinhLuan = new AdapterRcvHinhBinhLuan(context, R.layout.custom_layout_hinhbinhluan,
                                bitmapList, binhLuanModel, false);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
                        holder.rcvListAnhBinhLuan.setLayoutManager(layoutManager);
                        holder.rcvListAnhBinhLuan.setAdapter(adapterRcvHinhBinhLuan);
                        adapterRcvHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        int soBinhLuan = binhLuanModelList.size();
        if (soBinhLuan > 5){
            return 5;
        }else {
            return binhLuanModelList.size();
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
