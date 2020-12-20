package com.app.foody.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.OdauAdapter;
import com.app.foody.Controller.Interfaces.OdauInterfaces;
import com.app.foody.Model.Product;
import com.app.foody.Model.QuanAnModel;
import com.app.foody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class OdauController {
    QuanAnModel quanAnModel;
    Context context;
    OdauAdapter adapterRecyclerOdau;
    int soItemTiepTheo=0,limit=3;
    public OdauController(Context context) {
        this.context=context;
        quanAnModel=new QuanAnModel();
    }

    public void getDanhSachQuanAnController(Context context,final NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location viTriHienTai){
         final List<QuanAnModel> quanAnModelList=new ArrayList<>();
         RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
         recyclerOdau.setLayoutManager(layoutManager);
         adapterRecyclerOdau=new OdauAdapter(context, quanAnModelList, R.layout.custom_layout_recyclerview_odau);
         recyclerOdau.setAdapter(adapterRecyclerOdau);
         final OdauInterfaces odauInterface=new OdauInterfaces() {
             @Override
             public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                 final List<Bitmap> bitmaps=new ArrayList<>();
                 for(String linkhinh:quanAnModel.getHinhanhquanan()){
                     StorageReference storageHinhAnh  = FirebaseStorage.getInstance().getReference().child("hinhquanan").child(linkhinh);
                     final long ONE_MEGABYTE=1024*1024;
                     storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                         @Override
                         public void onSuccess(byte[] bytes) {
                             Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                             bitmaps.add(bitmap);
                             if(bitmaps.size()==quanAnModel.getHinhanhquanan().size()){
                                 quanAnModel.setBitmaps(bitmaps);
                                 quanAnModelList.add(quanAnModel);
                                 adapterRecyclerOdau.notifyDataSetChanged();
                                 progressBar.setVisibility(View.GONE);
                             }
                         }
                     });
                 }

             }

             @Override
             public void getDanhSachQuanAnModel(Product quanAnModel) {

             }
         };

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
             @Override
             public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                 if(nestedScrollView.getChildAt(nestedScrollView.getChildCount()-1)!=null){
                     if(scrollY>=(nestedScrollView.getChildAt(nestedScrollView.getChildCount()-1).getMeasuredHeight()-nestedScrollView.getMeasuredHeight())) {
                         soItemTiepTheo+=limit;
                         quanAnModel.getDanhSachQuanAn(odauInterface, viTriHienTai, soItemTiepTheo, soItemTiepTheo - limit);
                     }
                 }

             }
         });
        soItemTiepTheo+=limit;
        quanAnModel.getDanhSachQuanAn(odauInterface, viTriHienTai, soItemTiepTheo, 0);
 }

}
