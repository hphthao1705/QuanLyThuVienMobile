package com.example.qlthuvien.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.qlthuvien.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.qlthuvien.Adapters.AdapterViewPager;
import com.example.qlthuvien.Adapters.LoadAllAdapter;

import com.example.qlthuvien.Fragments.AllFragment;
import com.example.qlthuvien.Fragments.DetMayFragment;
import com.example.qlthuvien.Fragments.DuLichFragment;

import com.example.qlthuvien.Fragments.KeToanFragment;
import com.example.qlthuvien.Fragments.NgoaiNguFragment;
import com.example.qlthuvien.Fragments.SinhHocFragment;

import com.example.qlthuvien.Fragments.TinHocFragment;
import com.example.qlthuvien.Fragments.TrangChuFragment;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    String tendg = "";
    Integer madg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        addControls();

        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.page_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.page_favourite);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.page_search);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.page_cart_book);
                        break;
                    case 4:
                        bottomNavigationView.setSelectedItemId(R.id.page_information);
                        break;

                }
                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.page_home: {
                    //replaceFragment(new TrangChuFragment());
                    pagerMain.setCurrentItem(0);
                    break;
                }
                case R.id.page_favourite: {
                    //replaceFragment(new GioSachFragment());
                    pagerMain.setCurrentItem(1);
                    break;
                }
                case R.id.page_search:
                    //replaceFragment(new TaiKhoanFragment());
                    pagerMain.setCurrentItem(2);
                    break;
                case R.id.page_cart_book:
                    //replaceFragment(new YeuThichFragment());
                    pagerMain.setCurrentItem(3);
                    break;
                case R.id.page_information:
                    //replaceFragment(new YeuThichFragment());
                    pagerMain.setCurrentItem(4);
                    break;
                default:
                    //replaceFragment(new TrangChuFragment());
                    pagerMain.setCurrentItem(0);

            }
            return true;
        });
//        checkBack();
    }

//    private void checkBack()
//    {
//        String so = getIntent().getStringExtra("number");
//        if (so != null)
//        {
//            if (Integer.parseInt(so) == 10)
//            {
//                pagerMain.setCurrentItem(0);
//                bottomNavigationView.getMenu().findItem(R.id.page_home).setChecked(true);
//            }
//            else {
//                pagerMain.setCurrentItem(3);
//                bottomNavigationView.getMenu().findItem(R.id.tab_taikhoan).setChecked(true);
//            }
//        }
//    }

    public void addControls()
    {
        pagerMain = (ViewPager2) findViewById(R.id.pagerMain);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
    }

}