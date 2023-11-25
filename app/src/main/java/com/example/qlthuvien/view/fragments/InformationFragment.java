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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qlthuvien.databinding.FragmentUpdateInfUserBinding;
import com.example.qlthuvien.view.activities.LoginActivity;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.R;
import com.example.qlthuvien.databinding.FragmentInformationBinding;

import static com.example.qlthuvien.view.activities.LoginActivity.EMAIL;
import static com.example.qlthuvien.view.activities.LoginActivity.ID_DG;
import static com.example.qlthuvien.view.activities.LoginActivity.ID_SV;
import static com.example.qlthuvien.view.activities.LoginActivity.NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.MSSV;
import static com.example.qlthuvien.view.activities.LoginActivity.NGAYSINH;
import static com.example.qlthuvien.view.activities.LoginActivity.PASSWORD;
import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.USER_ID;
import static com.example.qlthuvien.view.activities.LoginActivity.userMSSV;
import static com.example.qlthuvien.view.activities.LoginActivity.GIOITINH;

import java.io.File;
import java.util.regex.Pattern;

public class InformationFragment extends Fragment {
    FragmentInformationBinding binding;
    MainActivity activity;

    private EditText edt_name, edt_mssv, edt_email, edt_pass, txt_repassword;
    String sdt, str_userid;
    int gtNam, gtNu, gioitinh;
    String email, pass, id_dg, id_sv, repass;
    String name, mssv, ngaysinh;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Button submit_btn, logout;

    LinearLayout expandableView;
    Pattern pattern_pwd = Pattern.compile("^[a-zA-Z0-9]+$");
    public static  int usergt;
    public static String userns = "", username = "", userpass = "", useremail = "",userid_dg = "", userid_sv = "";
    //    public static int userid_dg, userid_sv;
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
        View viewLogout = view.findViewById(R.id.view_logout);

//        sharedPreferences = getActivity().getSharedPreferences(LoginActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        str_userid = sharedPreferences.getString(USER_ID, "");
        name = sharedPreferences.getString(NAME, "");
        email = sharedPreferences.getString(EMAIL, "");
        mssv = sharedPreferences.getString(MSSV, "");
        pass = sharedPreferences.getString(PASSWORD, "");
        id_dg = sharedPreferences.getString(ID_DG, "");
        id_sv = sharedPreferences.getString(ID_SV, "");
        gioitinh = sharedPreferences.getInt(String.valueOf(GIOITINH), 0);
        ngaysinh = sharedPreferences.getString(NGAYSINH, "");

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
        }



        binding.viewLichsumuontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoggedIn) {
                    activity = (MainActivity) getActivity();
                    activity.replaceFragment(new HistoryFragment(0));
                }
                else
                {
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                }
            }
        });

        binding.viewYeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoggedIn) {
                    activity = (MainActivity) getActivity();
                    NavigationBottomFragment f = new NavigationBottomFragment();
                    f.setCurrent(new FavouriteFragment());
                    f.setMenu_bottom(R.id.page_favourite);
                    activity.replaceFragment(f);
                }
                else {
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                }
            }
        });

        binding.viewThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        Log.e("inf", "result" + id_dg);
        Log.e("ngaysinh", "result" + ngaysinh);

        Log.e("id_sv", "result" + id_sv);

        Log.e("ngaysinh", "result" + name);

        Log.e("id_sv", "result" + email);

        Log.e("inf", "result" + str_userid);
        Log.e("ngaysinh", "result" + gioitinh);


        TextView name_user = (TextView) view.findViewById(R.id.tv_nameuser);
        if (name_user != null) {
            name_user.setText(name);
        } else {
            Log.e("inf", "Không tìm thấy");
        }
    }

    private void logout() {
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_ID);
        editor.remove(MSSV);
        editor.remove(NAME);
        editor.remove(PASSWORD);
        editor.remove(ID_DG);
        editor.remove(ID_SV);
        editor.remove(String.valueOf(GIOITINH));
        editor.remove(NGAYSINH);
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