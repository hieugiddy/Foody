package com.app.foody.View;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.foody.Adapters.DuocDatNhieuAdapter;
import com.app.foody.Model.DuocDatNhieuItem;
import com.app.foody.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChiTietQuanAn extends AppCompatActivity {
    ListView lv;
    DuocDatNhieuAdapter adapter;
    ArrayList<DuocDatNhieuItem> arr_bean;
    ImageView chitiet_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietquanan);
        chitiet_back=findViewById(R.id.chitiet_back);

        chitiet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Listview
        lv = (ListView) findViewById(R.id.list_dn);
        arr_bean=new ArrayList<DuocDatNhieuItem>();
        arr_bean.add(new DuocDatNhieuItem(R.drawable.loading, "Gà sốt cay Hàn Quốc","Bán chạy nhất của quán","46,000đ"));
        arr_bean.add(new DuocDatNhieuItem(R.drawable.loading, "Gà sốt cay Hàn Quốc","Bán chạy nhất của quán","46,000đ"));
        arr_bean.add(new DuocDatNhieuItem(R.drawable.loading, "Gà sốt cay Hàn Quốc","Bán chạy nhất của quán","46,000đ"));
        arr_bean.add(new DuocDatNhieuItem(R.drawable.loading, "Gà sốt cay Hàn Quốc","Bán chạy nhất của quán","46,000đ"));
        arr_bean.add(new DuocDatNhieuItem(R.drawable.loading, "Gà sốt cay Hàn Quốc","Bán chạy nhất của quán","46,000đ"));
        arr_bean.add(new DuocDatNhieuItem(R.drawable.loading, "Gà sốt cay Hàn Quốc","Bán chạy nhất của quán","46,000đ"));
        adapter=new DuocDatNhieuAdapter(arr_bean,this);
        lv.setAdapter(adapter);

        //slide banner
        final ImageSlider imageSlider = findViewById(R.id.slide_anhquanan); // init imageSlider
        final List<SlideModel> imageList = new ArrayList<>(); // Create image list
        FirebaseDatabase.getInstance().getReference().child("slide")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren())
                            imageList.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.CENTER_CROP));
                        imageSlider.setImageList(imageList,ScaleTypes.CENTER_CROP);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        //
    }
}
