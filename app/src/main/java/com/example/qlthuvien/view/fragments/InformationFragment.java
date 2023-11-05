package com.example.qlthuvien.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.R;
import com.example.qlthuvien.databinding.FragmentInformationBinding;

import static com.example.qlthuvien.view.activities.LoginActivity.NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.EMAIL;
import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.USER_ID;
import static com.example.qlthuvien.view.activities.LoginActivity.ID_DG;

public class InformationFragment extends Fragment {
    FragmentInformationBinding binding;
    private TextView tv_name, tv_email;
    String name;
    SharedPreferences sharedPreferences;
    MainActivity activity;
    String id_dg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.btnXemthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                activity.replaceFragment(new InformationOfUserFragment());
            }
        });
        binding.viewLichsumuontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                activity.replaceFragment(new HistoryFragment());
            }
        });

        binding.viewYeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                activity.replaceFragment(new FavouriteFragment());
            }
        });

        binding.viewThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                activity.replaceFragment(new NotificationFragment());
            }
        });

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        name = sharedPreferences.getString(NAME, "");
        id_dg = sharedPreferences.getString(ID_DG, "");

        Log.e("inf", "result" + id_dg);

        TextView name_user = (TextView) view.findViewById(R.id.tv_nameuser);
        if (name_user != null) {
            name_user.setText(name);
        } else {
            Log.e("inf", "Không tìm thấy");
        }
    }
}