package com.app.foody.Adapters;

import android.content.Context;
import android.content.Intent;
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
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;
import com.app.foody.View.ChiTietQuanAn;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class XemGanDayAdapter extends  RecyclerView.Adapter<XemGanDayAdapter.ViewHolder> {
    List<QuanAnModel>quanAnModelList;
    int resource;
    Context context;

    public XemGanDayAdapter(Context context,List<QuanAnModel>quanAnModelList, int resource){
        this.quanAnModelList=quanAnModelList;
        this.resource=resource;
        this.context=context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView binhluan,tenquanan;
        ImageView txtimage;
        View viewTrangChuClick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtimage=itemView.findViewById(R.id.txtimage);
            tenquanan=itemView.findViewById(R.id.tenquanan);
            binhluan=itemView.findViewById(R.id.bl);
            viewTrangChuClick=itemView.findViewById(R.id.viewTrangChuClick);
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
        final QuanAnModel quanAnModel=quanAnModelList.get(position);
        holder.tenquanan.setText(quanAnModel.getTenquanan());
        if (quanAnModel.getHinhanhquanan().size()>0){
            holder.txtimage.setImageBitmap(quanAnModel.getBitmaps().get(0));
        }
        if (quanAnModel.getBinhLuanModelList().size()>0) {
            BinhLuanModel binhLuanModel = quanAnModel.getBinhLuanModelList().get(1);
            holder.binhluan.setText(binhLuanModel.getNoidung());

        } else {
            holder.binhluan.setText("Chưa có bình luận nào");
        }
        holder.viewTrangChuClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ChiTietQuanAn.class);
                i.putExtra("quanan", quanAnModel);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}





















