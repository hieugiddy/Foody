package com.app.foody.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.R;

import java.util.List;

public class AdapterHienThiHinhBinhLuan extends RecyclerView.Adapter<AdapterHienThiHinhBinhLuan.ViewHolderHinhBinhLuan> {
    Context context;
    int resource;
    List<String> list;

    public AdapterHienThiHinhBinhLuan(Context context, int resource, List<String> list) {
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderHinhBinhLuan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolderHinhBinhLuan viewHolderHinhBinhLuan=new ViewHolderHinhBinhLuan(view);
        return viewHolderHinhBinhLuan;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHinhBinhLuan holder, int position) {
        Uri uri=Uri.parse(list.get(position));
        holder.imageView.setImageURI(uri);
        holder.imgxoa.setTag(position);
        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vitri=(int)v.getTag();
                list.remove(vitri);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderHinhBinhLuan extends RecyclerView.ViewHolder {
        ImageView imageView,imgxoa;
        public ViewHolderHinhBinhLuan(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgchonhinhbinhluan);
            imgxoa=itemView.findViewById(R.id.imgDELETE);
        }
    }
}
