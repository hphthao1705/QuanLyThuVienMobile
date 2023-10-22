package com.example.qlthuvien.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.qlthuvien.databinding.ActivityMainBinding;
import com.example.qlthuvien.view.fragments.NavigationBottomFragment;
import com.example.qlthuvien.R;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();

        Fragment fragment = new NavigationBottomFragment();
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frameLayout1.getId(), fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

}