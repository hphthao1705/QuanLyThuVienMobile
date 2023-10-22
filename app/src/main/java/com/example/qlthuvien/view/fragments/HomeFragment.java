package com.example.qlthuvien.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.qlthuvien.R;
import com.example.qlthuvien.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    public HomeFragment(int i){
        _i = i;
    }
    int _i;
    FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(_i == 1)
        {
            replaceFragment(new CategoryFragment());
        }
        else
        {
            replaceFragment(new HomePageFragment());

        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(binding.framelayout3.getId(), fragment).commit();
    }
}