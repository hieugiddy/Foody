package com.app.foody.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.ChonhinhBinhluanModel;
import com.app.foody.R;

import java.util.List;

public class AdapterChonhinhBinhluan  extends RecyclerView.Adapter<AdapterChonhinhBinhluan.ViewHolderChonHinh> {
    Context context;
    int resource;
    List<ChonhinhBinhluanModel>listduongdan;
    public AdapterChonhinhBinhluan(Context context, int resource, List<ChonhinhBinhluanModel>listduongdan) {
            this.context=context;
            this.resource=resource;
            this.listduongdan=listduongdan;
    }

    @NonNull
    @Override
    public AdapterChonhinhBinhluan.ViewHolderChonHinh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolderChonHinh viewHolderChonHinh=new ViewHolderChonHinh(view);
        return viewHolderChonHinh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChonhinhBinhluan.ViewHolderChonHinh holder, final int position) {
            final ChonhinhBinhluanModel chonhinhBinhluanModel=listduongdan.get(position);
        Uri uri=Uri.parse(chonhinhBinhluanModel.getDuongdan());
        holder.imageView.setImageURI(uri);
        holder.checkBox.setChecked(chonhinhBinhluanModel.isIscheck());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox=(CheckBox)v;
             //   chonhinhBinhluanModel.setIscheck(checkBox.isChecked());
                listduongdan.get(position).setIscheck(checkBox.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listduongdan.size();
    }

    public class ViewHolderChonHinh extends RecyclerView.ViewHolder {
            ImageView imageView;
            CheckBox checkBox;

        public ViewHolderChonHinh(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imgchonhinhbinhluan);
            checkBox=itemView.findViewById(R.id.checkchonhinh);
        }
    }
}
