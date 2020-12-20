package com.app.foody.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.Hori2;
import com.app.foody.R;

import java.util.List;

public class Hori2Adapter extends RecyclerView.Adapter<Hori2Adapter.HorizontalViewHolder>{
    private List<Hori2> mListUser;
    public void setData(List<Hori2> list){
        this.mListUser = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hori2, parent, false);

        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        Hori2 hori2 = mListUser.get(position);
        if(hori2 == null){
            return;
        }
        holder.imgAvatar.setImageResource(hori2.getResourceID());
        holder.tvTitle.setText(hori2.getTitle());
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
        private TextView tvTitle;


        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }


}
