package com.app.foody.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.Hori1;
import com.app.foody.R;

import java.util.List;

public class Hori1Adapter extends RecyclerView.Adapter<Hori1Adapter.HorizontalViewHolder>{
    private List<Hori1> mListUser;
    public void setData(List<Hori1> list){
        this.mListUser = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hori1, parent, false);

        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        Hori1 hori1 = mListUser.get(position);
        if(hori1 == null){
            return;
        }
        holder.imgAvatar.setImageResource(hori1.getResourceID());
        holder.tvName.setText(hori1.getName());
    }

    @Override
    public int getItemCount() {
        if(mListUser != null){
            return mListUser.size();
        }
        return 0;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgAvatar;
        private TextView tvName;


        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }


}
