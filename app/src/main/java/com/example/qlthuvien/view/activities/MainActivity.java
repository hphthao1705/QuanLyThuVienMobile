package com.example.qlthuvien.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.databinding.ActivityMainBinding;
import com.example.qlthuvien.view.fragments.NavigationBottomFragment;
import com.example.qlthuvien.R;
import com.example.qlthuvien.viewmodels.DocGiaViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private DocGiaViewModel viewModel_DG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();

        viewModel_DG = new ViewModelProvider(this).get(DocGiaViewModel.class);

        Fragment fragment = new NavigationBottomFragment();
        replaceFragment(fragment);

        loadUser();
    }

    public void replaceFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frameLayout1.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadUser()
    {
        viewModel_DG.getData().observe(this, new Observer<List<DocGia>>() {
            @Override
            public void onChanged(List<DocGia> docGias) {
                //Toast.makeText(MainActivity.this, String.valueOf(docGias.size()), Toast.LENGTH_LONG).show();
            }
        });
    }

}