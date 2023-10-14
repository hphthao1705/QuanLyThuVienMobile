package com.example.qlthuvien.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.qlthuvien.R;
import com.google.android.material.tabs.TabLayout;

import com.example.qlthuvien.Adapters.AdapterViewPager;

import com.example.qlthuvien.Fragments.AllFragment;
import com.example.qlthuvien.Fragments.DetMayFragment;
import com.example.qlthuvien.Fragments.DuLichFragment;

import com.example.qlthuvien.Fragments.KeToanFragment;
import com.example.qlthuvien.Fragments.NgoaiNguFragment;
import com.example.qlthuvien.Fragments.SinhHocFragment;

import com.example.qlthuvien.Fragments.TinHocFragment;
import com.example.qlthuvien.Fragments.TrangChuFragment;


import java.util.ArrayList;


public class TheLoai extends AppCompatActivity {
    TabLayout tabLayout;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    String tendg = "";
    String madg = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        tabLayout = (TabLayout) findViewById(R.id.tab_theloai);
        replaceFrag(new AllFragment());
        Intent intent = getIntent();
        madg = intent.getStringExtra("madg");
        System.out.println("t mún đi ngủ"+madg);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:

                        replaceFrag(new AllFragment());

                        break;
                    case 1:
                        replaceFrag(new TinHocFragment());
                        break;
                    case 2:
                        replaceFrag(new NgoaiNguFragment());
                        break;
                    case 3:
                        replaceFrag(new KeToanFragment());
                        break;
                    case 4:
                        replaceFrag(new DuLichFragment());
                        break;
                    case 5:
                        replaceFrag(new DetMayFragment());
                        break;
                    case 6:
                        replaceFrag(new SinhHocFragment());
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

//        goiTab_CardView();
        //backTheLoai();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.page_home);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }
    public void replaceFrag(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }



}