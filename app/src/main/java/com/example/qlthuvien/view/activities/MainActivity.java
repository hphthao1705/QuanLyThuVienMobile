package com.example.qlthuvien.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.qlthuvien.databinding.ActivityMainBinding;
import com.example.qlthuvien.view.fragments.NavigationBottomFragment;
import com.example.qlthuvien.R;
import com.example.qlthuvien.viewmodels.DocGiaViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();

        DocGiaViewModel viewModel_DG = new ViewModelProvider(this).get(DocGiaViewModel.class);

        Fragment fragment = new NavigationBottomFragment();
        replaceFragment(fragment);



        viewModel_DG.liveData_DG.observe(this, it->{
            Log.d("Thao",it.toString());
        });

    }

    public void replaceFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frameLayout1.getId(), fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

}