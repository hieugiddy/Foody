package com.app.foody.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.BinhLuanModel;
import com.app.foody.Model.Product;
import com.app.foody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class XemGanDayAdapter extends  RecyclerView.Adapter<XemGanDayAdapter.ViewHolder> {
    List<Product>quanAnModelList;
    int resource;
    public XemGanDayAdapter(List<Product>quanAnModelList, int resource){
        this.quanAnModelList=quanAnModelList;
        this.resource=resource;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView binhluan,tenquanan;
        ImageView txtimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtimage=itemView.findViewById(R.id.txtimage);
            tenquanan=itemView.findViewById(R.id.tenquanan);
            binhluan=itemView.findViewById(R.id.bl);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder  ;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Product quanAnModel=quanAnModelList.get(position);
        holder.tenquanan.setText(quanAnModel.getTenquanan());
        if (quanAnModel.getHinhanhquanan().size()>0){
            StorageReference storageHinhAnh  = FirebaseStorage.getInstance().getReference().child("hinhquanan").child(quanAnModel.getHinhanhquanan().get(0));
            final long ONE_MEGABYTE=1024*1024;
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            holder.txtimage.setImageBitmap(bitmap);
                }
            });
        }
        if (quanAnModel.getBinhLuanModelList().size()>0) {
            BinhLuanModel binhLuanModel = quanAnModel.getBinhLuanModelList().get(1);
            holder.binhluan.setText(binhLuanModel.getNoidung());

        } else {
            holder.binhluan.setText("Chưa có bình luận nào");
        }
    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}





















