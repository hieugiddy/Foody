package com.app.foody.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.foody.Adapters.GanToiListViewBaseAdapter_GiaoNhanh;
import com.app.foody.Model.GanToiListViewBean_GiaoNhanh;
import com.app.foody.R;

import java.util.ArrayList;

public class FragmentDanhGia_GiaoNhanh extends Fragment {
    private View mRootView;
    ListView listViewGanToi;
    GanToiListViewBaseAdapter_GiaoNhanh listViewBaseAdapterGanToi;
    ArrayList<GanToiListViewBean_GiaoNhanh> arrayListGanToi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.giaonhanh_danhgia,container,false);
        listViewGanToi = (ListView)mRootView.findViewById(R.id.lvDanhGia);
        arrayListGanToi = new ArrayList<>();
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.trasua, "Trà sữa Money", "15.000đ", "10km", "15'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.dabao, "Đá bào sôcôla", "20.000đ", "1km", "15'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.gantoi_lau, "Lẩu thịt bò", "180.000đ", "7.5km", "20'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.cua, "Cua hấp", "40.000đ", "3.67km", "10'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.bap, "Bắp rang", "10.000đ", "5km", "5'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.trasua, "Trà sữa Money", "15.000đ", "10km", "15'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.dabao, "Đá bào sôcôla", "20.000đ", "1km", "15'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.gantoi_lau, "Lẩu thịt bò", "180.000đ", "7.5km", "20'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.cua, "Cua hấp", "40.000đ", "3.67km", "10'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.bap, "Bắp rang", "10.000đ", "5km", "5'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.dabao, "Đá bào sôcôla", "20.000đ", "1km", "15'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.gantoi_lau, "Lẩu thịt bò", "180.000đ", "7.5km", "20'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.cua, "Cua hấp", "40.000đ", "3.67km", "10'"));
        arrayListGanToi.add(new GanToiListViewBean_GiaoNhanh(R.drawable.bap, "Bắp rang", "10.000đ", "5km", "5'"));
        listViewBaseAdapterGanToi = new GanToiListViewBaseAdapter_GiaoNhanh(arrayListGanToi,getActivity());
        listViewGanToi.setAdapter(listViewBaseAdapterGanToi);
        return mRootView;
    }
}
