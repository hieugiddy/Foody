package com.app.foody.View;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foody.Adapters.Hori1Adapter;
import com.app.foody.Adapters.Hori2Adapter;
import com.app.foody.Adapters.Ver1Adapter;
import com.app.foody.Model.Hori1;
import com.app.foody.Model.Hori2;
import com.app.foody.Model.Ver1;
import com.app.foody.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class DatChoView extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private RecyclerView rcvTab1;
    private RecyclerView rcvTab2;
    private RecyclerView rcvTab3;
    private RecyclerView rcvTab4;
    private RecyclerView rcv1;
    private RecyclerView rcv2;
    private RecyclerView rcv3;
    private RecyclerView rcv4;

    private Ver1Adapter ver1Adapter;
    private Ver1Adapter ver2Adapter;
    private Ver1Adapter ver3Adapter;
    private Ver1Adapter ver4Adapter;
    private Hori1Adapter hori1Adapter;
    private Hori2Adapter hori2Adapter;
    private Hori2Adapter hori2Adapter1;
    private Hori2Adapter hori2Adapter2;

    private Context context;
    String arr[] = {"Đà Nẵng","TP.HCM","B.Dương","H.Phòng"};
    TextView selection;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datcho_activity);
        initUi();
        initRecyclerView();
        tab();

        /////////////////
        selection =(TextView) findViewById(R.id.selection);
        Spinner spin=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new MyProcessEvent());
        ///////////////

    }

    ////================
    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            selection.setText(arr[arg2]);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
            selection.setText("");
        }
    }
    //================

    private void initUi(){
        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);


        rcvTab1 = findViewById(R.id.rcv_tab1);
        rcvTab2 = findViewById(R.id.rcv_tab2);
        rcvTab3 = findViewById(R.id.rcv_tab3);
        rcvTab4 = findViewById(R.id.rcv_tab4);
        rcv1 = findViewById(R.id.rcv1);
        rcv2 = findViewById(R.id.rcv2);
        rcv3 = findViewById(R.id.rcv3);
        rcv4 = findViewById(R.id.rcv4);

    }

    private void initRecyclerView(){
        rcvTab1.setHasFixedSize(true);
        rcvTab2.setHasFixedSize(true);
        rcvTab3.setHasFixedSize(true);
        rcvTab4.setHasFixedSize(true);
        rcv1.setHasFixedSize(true);
        rcv2.setHasFixedSize(true);
        rcv3.setHasFixedSize(true);
        rcv4.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager6 = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager7 = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager8 = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);

        rcvTab1.setLayoutManager(linearLayoutManager);
        rcvTab2.setLayoutManager(linearLayoutManager6);
        rcvTab3.setLayoutManager(linearLayoutManager7);
        rcvTab4.setLayoutManager(linearLayoutManager8);
        rcv1.setLayoutManager(linearLayoutManager2);
        rcv2.setLayoutManager(linearLayoutManager3);
        rcv3.setLayoutManager(linearLayoutManager4);
        rcv4.setLayoutManager(linearLayoutManager5);

        ver1Adapter = new Ver1Adapter();
        ver2Adapter = new Ver1Adapter();
        ver3Adapter = new Ver1Adapter();
        ver4Adapter = new Ver1Adapter();
        hori1Adapter = new Hori1Adapter();
        hori2Adapter = new Hori2Adapter();
        hori2Adapter1 = new Hori2Adapter();
        hori2Adapter2 = new Hori2Adapter();

        ver1Adapter.setData(getListVer1());
        ver2Adapter.setData(getListVer2());
        ver3Adapter.setData(getListVer3());
        ver4Adapter.setData(getListVer4());
        hori1Adapter.setData(getListHori1());
        hori2Adapter.setData(getListHori2());
        hori2Adapter1.setData(getListHori3());
        hori2Adapter2.setData(getListHori4());

        rcvTab1.setAdapter(ver1Adapter);
        rcvTab2.setAdapter(ver2Adapter);
        rcvTab3.setAdapter(ver3Adapter);
        rcvTab4.setAdapter(ver4Adapter);
        rcv1.setAdapter(hori1Adapter);
        rcv2.setAdapter(hori2Adapter);
        rcv3.setAdapter(hori2Adapter1);
        rcv4.setAdapter(hori2Adapter2);
    }

    private List<Ver1> getListVer1(){
        List<Ver1> list = new ArrayList<>();
        list.add(new Ver1(R.drawable.ic_avatar1, "Nổi bật 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 2", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 3", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 2", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 3", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 2", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Nổi bật 3", "Da Nang"));
        return list;
    }
    private List<Ver1> getListVer2(){
        List<Ver1> list = new ArrayList<>();
        list.add(new Ver1(R.drawable.ic_avatar1, "Đặt nhiều 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Đặt nhiều 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Đặt nhiều 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Đặt nhiều 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Đặt nhiều 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Đặt nhiều 1", "Da Nang"));
        return list;
    }
    private List<Ver1> getListVer3(){
        List<Ver1> list = new ArrayList<>();
        list.add(new Ver1(R.drawable.ic_avatar1, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Mới 1", "Da Nang"));

        return list;
    }
    private List<Ver1> getListVer4(){
        List<Ver1> list = new ArrayList<>();
        list.add(new Ver1(R.drawable.ic_avatar1, "Gần tôi 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Gần tôi 2", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Gần tôi 3", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar1, "Gần tôi 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Gần tôi 2", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Gần tôi 3", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar1, "Gần tôi 1", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Gần tôi 2", "Da Nang"));
        list.add(new Ver1(R.drawable.ic_avatar2, "Gần tôi 3", "Da Nang"));
        return list;
    }

    private List<Hori1> getListHori1(){
        List<Hori1> list2 = new ArrayList<>();
        list2.add(new Hori1(R.drawable.ic_avatar1, "Deal mới"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "Độc quyền"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "Buffer"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "Sale"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "Quà khủng"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "User name 2"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "User name 2"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "User name 2"));
        list2.add(new Hori1(R.drawable.ic_avatar2, "User name 2"));
        return list2;
    }
    private List<Hori2> getListHori2(){
        List<Hori2> list3 = new ArrayList<>();
        list3.add(new Hori2(R.drawable.my, "Deal mới"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        list3.add(new Hori2(R.drawable.my, "Độc quyền"));
        return list3;
    }
    private List<Hori2> getListHori3(){
        List<Hori2> list4 = new ArrayList<>();
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        list4.add(new Hori2(R.drawable.my, "Deal mới"));
        return list4;
    }
    private List<Hori2> getListHori4(){
        List<Hori2> list5 = new ArrayList<>();
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));
        list5.add(new Hori2(R.drawable.my, "Deal mới"));

        return list5;
    }

    public void tab()
    {
        //bắt đầu tabhost
        TabHost tabhost = (TabHost) findViewById(R.id.tabhost);
        // setting up the tab host
        tabhost.setup();

        // Code thêm Tab 1 vào tabhost
        TabHost.TabSpec spec = tabhost.newTabSpec("Đang Đến");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Nổi bật");
        tabhost.addTab(spec);

        // Code thêm Tab 2 vào tabhost
        spec = tabhost.newTabSpec("Lịch Sử");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Đặt nhiều");
        tabhost.addTab(spec);

        // Code thêm Tab 3 vào  tabhost
        spec = tabhost.newTabSpec("Đơn Nháp");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Mới");
        tabhost.addTab(spec);

        // Code thêm Tab 4 vào  tabhost
        spec = tabhost.newTabSpec("Đơn Nháp");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Gần tôi");
        tabhost.addTab(spec);
    }



}























