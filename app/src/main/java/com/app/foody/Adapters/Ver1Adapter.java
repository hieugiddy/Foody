package com.app.foody.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Model.Ver1;
import com.app.foody.R;

import java.util.List;

public class Ver1Adapter extends RecyclerView.Adapter<Ver1Adapter.UserViewHolder>{
    private List<Ver1> mListVer1;
    public void setData(List<Ver1> list){
        this.mListVer1 = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ver1, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Ver1 ver1 = mListVer1.get(position);
        if(ver1 == null){
            return;
        }
        holder.imgAvatar.setImageResource(ver1.getResourceID());
        holder.tvName.setText(ver1.getName());
        holder.tvAddress.setText(ver1.getAddress());
    }

    @Override
    public int getItemCount() {
        if(mListVer1 != null){
            return mListVer1.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgAvatar;
        private TextView tvName;
        private TextView tvAddress;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }


}
