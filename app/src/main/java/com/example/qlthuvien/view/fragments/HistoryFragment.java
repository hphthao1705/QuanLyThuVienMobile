package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.R;
import com.example.qlthuvien.databinding.FragmentHistoryBinding;
import com.example.qlthuvien.view.activities.MainActivity;

public class HistoryFragment extends Fragment {

    FragmentHistoryBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnRcvDangxuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryPartFragment f = new HistoryPartFragment(1);
                replaceFragment(f);
            }
        });

        binding.btnRcvLichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryPartFragment f = new HistoryPartFragment(0);
                replaceFragment(f);
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                NavigationBottomFragment f = new NavigationBottomFragment();
                f.setCurrent(new InformationFragment());
                f.setMenu_bottom(R.id.page_information);
                activity.replaceFragment(f);
            }
        });

    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(binding.frameLayout4.getId(), fragment).commit();
    }
}