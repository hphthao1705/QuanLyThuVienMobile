package com.example.qlthuvien.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.qlthuvien.Fragments.CartBookFragment;
import com.example.qlthuvien.Fragments.FavouriteFragment;
import com.example.qlthuvien.Fragments.HomeFragment;
import com.example.qlthuvien.Fragments.InformationFragment;
import com.example.qlthuvien.Fragments.SearchFragment;
import com.example.qlthuvien.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment current = new HomeFragment();
    Integer current_page = R.id.page_home;

    Integer PageHome  = R.id.page_home;
    Integer PageFavourite  = R.id.page_favourite;
    Integer PageSearch  = R.id.page_search;
    Integer PageCartBook  = R.id.page_cart_book;
    Integer PageInformation  = R.id.page_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set Home selected
        Intent i = getIntent();
        loadFragment(current);
        bottomNavigationView.setSelectedItemId(current_page);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.page_home)
                {
                    fragment = new HomeFragment();
                }
                else  if (item.getItemId() == R.id.page_favourite)
                {
                    fragment = new FavouriteFragment();
                }
                else  if (item.getItemId() == R.id.page_search)
                {
                    fragment = new SearchFragment();
                }
                else  if (item.getItemId() == R.id.page_cart_book)
                {
                    fragment = new CartBookFragment();
                }
                else  if (item.getItemId() == R.id.page_information)
                {
                    fragment = new InformationFragment();
                }
                if(fragment == null)
                {

                    return false;
                }
                loadFragment(fragment);
                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

}