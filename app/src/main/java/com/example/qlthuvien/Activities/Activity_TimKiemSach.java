package com.example.qlthuvien.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.qlthuvien.Adapters.timkiem;
import com.example.qlthuvien.Class.TaiLieu;
import com.example.qlthuvien.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Activity_TimKiemSach extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    List<TaiLieu> mlistTaiLieu;

    private timkiem linhAdapter_timkiem;
    private Button btn_hienthi;
    private LinearLayout lnear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_sach);
        getSupportActionBar().hide();
        addControls();

        searchView.clearFocus();
        searchView.requestFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });



        recyclerView.setAdapter(linhAdapter_timkiem);



        mlistTaiLieu = new ArrayList<>();
        linhAdapter_timkiem = new timkiem(mlistTaiLieu);


        linhAdapter_timkiem = new timkiem(mlistTaiLieu);
        recyclerView.setAdapter(linhAdapter_timkiem);
    }

    public void addControls()
    {
        searchView = (SearchView) findViewById(R.id.searchView_tk);
        recyclerView = (RecyclerView) findViewById(R.id.rec_timkiem);

        lnear = (LinearLayout) findViewById(R.id.linear);
    }

    private void searchList(String text) {
        List<TaiLieu> filterList = new ArrayList<>();
        for(TaiLieu taiLieu : mlistTaiLieu)
        {
            if (taiLieu.getTenSach().toLowerCase(Locale.ROOT).contains(text.toLowerCase()))
            {
                filterList.add(taiLieu);

            }
        }
        if (filterList.isEmpty())
        {
            Toast.makeText(this, "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
            //int a = 0;
        }
        else {
            linhAdapter_timkiem.setSearchList(filterList);
        }
    }

}