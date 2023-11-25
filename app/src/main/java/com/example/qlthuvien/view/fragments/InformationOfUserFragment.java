package com.example.qlthuvien.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlthuvien.databinding.FragmentInformationOfUserBinding;
import com.example.qlthuvien.view.activities.LoginActivity;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.R;
import com.example.qlthuvien.view.activities.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import static com.example.qlthuvien.view.activities.LoginActivity.EMAIL;
import static com.example.qlthuvien.view.activities.LoginActivity.GIOITINH;
import static com.example.qlthuvien.view.activities.LoginActivity.NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.MSSV;
import static com.example.qlthuvien.view.activities.LoginActivity.NGAYSINH;
import static com.example.qlthuvien.view.activities.LoginActivity.PASSWORD;
import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.USER_ID;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationOfUserFragment extends Fragment {
    FragmentInformationOfUserBinding binding;
    MainActivity activity;

    TextView tv_name, tv_email;
    String name , email, sdt, pass, mssv, str_userid, ngaysinh;
    int gt;
    SharedPreferences sharedPreferences;


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

        binding.tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                activity.replaceFragment(new UpdateInfUserFragment());
            }
        });


        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        str_userid = sharedPreferences.getString(USER_ID, "");
        name = sharedPreferences.getString(NAME, "");
        email = sharedPreferences.getString(EMAIL, "");
        mssv = sharedPreferences.getString(MSSV, "");
        pass = sharedPreferences.getString(PASSWORD, "");
        ngaysinh = sharedPreferences.getString(NGAYSINH, "");
        gt = sharedPreferences.getInt(String.valueOf(GIOITINH), 0);

        Log.e("inf", email);
        TextView name_user = (TextView) view.findViewById(R.id.tv_email);
        if (name_user != null) {
            name_user.setText(name);
        } else {
            Log.e("inf", "Không tìm thấy");
        }

        TextView mssv_user = (TextView) view.findViewById(R.id.tv_mssv);
        if (name_user != null) {
            mssv_user.setText(mssv);
        } else {
            Log.e("inf", "Không tìm thấy");
        }

        TextView ngaysinh_user = (TextView) view.findViewById(R.id.tv_ngaysinh);
        if (name_user != null) {
            ngaysinh_user.setText(ngaysinh);
        } else {
            Log.e("inf", "Không tìm thấy");
        }

        TextView gt_user = (TextView) view.findViewById(R.id.tv_gt);
        if (name_user != null) {
            if(gt == 0)
            {
                gt_user.setText("Nữ");
            }
            else
                gt_user.setText("Nam");

        } else {
            Log.e("inf", "Không tìm thấy");
        }

    }



}