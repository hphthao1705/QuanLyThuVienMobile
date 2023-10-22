package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.R;
import com.example.qlthuvien.databinding.FragmentNavigationBottomBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationBottomFragment extends Fragment {
    FragmentNavigationBottomBinding binding;
    Fragment current = new FavouriteFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation_bottom, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clickOnNavigationBottom();
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(binding.frameLayout2.getId(), fragment).commit();
    }

    private void clickOnNavigationBottom()
    {
        replaceFragment(current);

        binding.bottomNavigation.setSelectedItemId(R.id.page_favourite);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                replaceFragment(fragment);
                return true;
            }
        });
    }
    private void check () {

    }
}