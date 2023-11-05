package com.example.qlthuvien.view.fragments;

import android.content.Context;
import android.content.Intent;
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

import com.example.qlthuvien.view.activities.LoginActivity;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.R;
import com.example.qlthuvien.databinding.FragmentInformationBinding;

import static com.example.qlthuvien.view.activities.LoginActivity.GIOITINH;
import static com.example.qlthuvien.view.activities.LoginActivity.ID_SV;
import static com.example.qlthuvien.view.activities.LoginActivity.MSSV;
import static com.example.qlthuvien.view.activities.LoginActivity.NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.EMAIL;
import static com.example.qlthuvien.view.activities.LoginActivity.NS;
import static com.example.qlthuvien.view.activities.LoginActivity.PASSWORD;
import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.USER_ID;
import static com.example.qlthuvien.view.activities.LoginActivity.ID_DG;

import java.io.File;

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

        View viewInf = view.findViewById(R.id.view_inf);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.contains(LoginActivity.ID_DG);

        binding.btnXemthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoggedIn) {
                    activity = (MainActivity) getActivity();
                    activity.replaceFragment(new InformationOfUserFragment());
                } else {
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                }
            }
        });

        if (isLoggedIn) {
            binding.btnXemthongtin.setText("Xem thông tin");
        } else {
            binding.btnXemthongtin.setText("Vui lòng đăng nhập");
            viewInf.setVisibility(View.GONE);
        }




//        binding.viewLichsumuontra.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity = (MainActivity) getActivity();
//                activity.replaceFragment(new HistoryFragment());
//            }
//        });

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

        binding.viewDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
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

    private void logout() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_ID);
        editor.remove(MSSV);
        editor.remove(NAME);
        editor.remove(PASSWORD);
        editor.remove(ID_DG);
        editor.remove(ID_SV);
        editor.remove(String.valueOf(GIOITINH));
        editor.remove(NS);
        editor.remove(EMAIL);
        editor.apply();

//        File file = new File("/data/data/com.trangchu_user/shared_prefs/tk_mk.xml");
//        if(file.exists())
//        {
//            file.delete();
//        }

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}