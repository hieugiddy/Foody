package com.app.foody.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.AdapterRecyclerView_GiaoNhanh;
import com.app.foody.Model.GanToiListViewBean_GiaoNhanh;
import com.app.foody.R;

import java.util.ArrayList;

public class FragmentRecycleView_GiaoNhanh extends Fragment {
    private View view;
    RecyclerView recyclerView;
    private AdapterRecyclerView_GiaoNhanh ganToiListViewBaseAdapter;
    ArrayList<GanToiListViewBean_GiaoNhanh> ganToiListViewBeans;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.giaonhanh__recycleview,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rc_gantoi);
        ganToiListViewBeans = new ArrayList<>();
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"123","123","123",""));
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"123","123","123",""));
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"","","",""));
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"","","",""));
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"","","",""));
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"","","",""));
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"","","",""));
        ganToiListViewBeans.add(new GanToiListViewBean_GiaoNhanh(R.drawable.sieusale_2,"","","",""));
        recyclerView.setHasFixedSize(true);
        // set up the RecyclerView
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        ganToiListViewBaseAdapter = new AdapterRecyclerView_GiaoNhanh(getActivity(),ganToiListViewBeans);
        recyclerView.setAdapter(ganToiListViewBaseAdapter);
        return view;
    }
}
