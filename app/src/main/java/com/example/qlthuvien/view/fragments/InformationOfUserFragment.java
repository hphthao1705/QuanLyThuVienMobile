package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.databinding.FragmentInformationOfUserBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.R;

public class InformationOfUserFragment extends Fragment {
    FragmentInformationOfUserBinding binding;
    MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_of_user, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                NavigationBottomFragment f = new NavigationBottomFragment();
                f.setCurrent(new InformationFragment());
                f.setMenu_bottom(R.id.page_information);
                activity.replaceFragment(f);
            }
        });
    }
}