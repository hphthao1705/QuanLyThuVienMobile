package com.example.qlthuvien.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.SinhVien;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.databinding.FragmentUpdateInfUserBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.R;
import com.example.qlthuvien.view.activities.RegisterActivity;
import com.google.android.material.snackbar.Snackbar;

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

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInfUserFragment extends Fragment {
    FragmentUpdateInfUserBinding binding;
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
    Pattern patternDate = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    public static  int usergt;
    public static String userns = "", username = "", userpass = "", useremail = "",userid_dg = "", userid_sv = "";
//    public static int userid_dg, userid_sv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_inf_user, container, false);
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



        // text view
        edt_name = (EditText) view.findViewById(R.id.edt_name);
        edt_mssv = (EditText) view.findViewById(R.id.edt_mssv);
        edt_pass = (EditText) view.findViewById(R.id.edt_pass);

        txt_repassword = (EditText) view.findViewById(R.id.edt_repass);

        expandableView = (LinearLayout) view.findViewById(R.id.expandableView);

        submit_btn = view.findViewById(R.id.update_btn);


        TextView mssv_user = (TextView) view.findViewById(R.id.edt_mssv);
        if (mssv_user != null) {
            mssv_user.setText(mssv);
        } else {
            Log.e("inf", "Không tìm thấy");
        }

        TextView name_user = (TextView) view.findViewById(R.id.edt_name);
        if (name_user != null) {
            name_user.setText(name);
        } else {
            Log.e("inf", "Không tìm thấy");
        }

        TextView ngaysinh_user = (TextView) view.findViewById(R.id.edt_ngaysinh);
        if (ngaysinh_user != null) {
            ngaysinh_user.setText(ngaysinh);
        } else {
            Log.e("inf", "Không tìm thấy");
        }

        Log.i("user id", String.valueOf(ngaysinh_user));

        RadioButton rad_nam = (RadioButton) view.findViewById(R.id.rad_nam);
        RadioButton rad_nu = (RadioButton) view.findViewById(R.id.rad_nu);
        if(rad_nam != null)
        {
            rad_nam.setChecked(true);
        }
        else
            rad_nu.setChecked(true);


        Log.i("user id", String.valueOf(id_sv));
        Log.i("user id", String.valueOf(id_dg));


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = name_user.getText().toString().trim();
                mssv = mssv_user.getText().toString().trim();
                ngaysinh = ngaysinh_user.getText().toString().trim();

                Log.d("ngaysinh", "" + ngaysinh);

                if (rad_nam.isChecked()) {
                    gioitinh = 1;
                } else
                    gioitinh = 0;


                pass = edt_pass.getText().toString();
                repass = txt_repassword.getText().toString();


                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!ngaysinh.isEmpty() && patternDate.matcher(ngaysinh).matches()) {
                        if (!pass.isEmpty() && pattern_pwd.matcher(pass).matches()) {

                            if (!mssv.isEmpty() && pattern_pwd.matcher(mssv).matches()) {
                                if (pass.equals(repass)) {
                                    Log.d("updateData", "" + ngaysinh);
                                    updateData();
                                } else {
                                    Snackbar.make(expandableView, "Mật khẩu và nhập mật khẩu không trùng khớp", Snackbar.LENGTH_SHORT).show();
                                }
                            } else {
                                Snackbar.make(expandableView, "Enter the Valid MSSV", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(expandableView, "Nhập lại mật khẩu", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Snackbar.make(expandableView, "sai định dạng ngày sinh (YYYY-MM-DD)", Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(expandableView, "Nhập lại email", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void updateData() {


        APIService api = Common.apiService;

//        Call<UpdateUserResponse> call = api.updateUser(id_dg, id_sv, email, pass, str_userid);

        DocGia docGiaToUpdate = new DocGia();
        docGiaToUpdate.setId_dg(Integer.parseInt(id_dg));
        docGiaToUpdate.setId_sv(Integer.parseInt(id_sv));
        docGiaToUpdate.setEmail(email);
        docGiaToUpdate.setPassword(pass);

        SinhVien sinhVienUpdate = new SinhVien();
        sinhVienUpdate.setId_sv(Integer.parseInt(id_sv));
        sinhVienUpdate.setMssv(mssv);
        sinhVienUpdate.setTensv(name);
        sinhVienUpdate.setGioitinh(gioitinh);
        sinhVienUpdate.setNgaysinh(ngaysinh);


        Call<DocGia> call = api.updateUser(Integer.parseInt(id_dg), docGiaToUpdate);

        call.enqueue(new Callback<DocGia>() {
            @Override
            public void onResponse(Call<DocGia> call, Response<DocGia> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Log.i("onSuccess", response.body().getEmail());
                        useremail = response.body().getEmail();
                        userpass = response.body().getPassword();


                        Common.apiService.updateUserSV(Integer.parseInt(id_sv), sinhVienUpdate).enqueue(new Callback<SinhVien>() {
                            @Override
                            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {

                                        userMSSV = response.body().getMssv();
                                        username = response.body().getTensv();

                                        userns = response.body().getNgaysinh();


                                        usergt = response.body().getGioitinh();


                                        try {
                                            Log.d("ParseUpdateData", "" + ngaysinh);
                                            parseUpdateData();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<SinhVien> call, Throwable t) {
                                Log.d("LoginBHai_error", "onFailure: " + "Throw" + t.toString());
                            }
                        });
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DocGia> call, Throwable t) {

                Log.d("if_o f gh_book", "onFailure: " + t);

            }
        });

    }

    private void parseUpdateData() {
        try {
            if (!useremail.isEmpty()) {
                saveInfo();
            } else {
                Log.d("loginresponse", "empty");
            }
            Toast.makeText(getActivity(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getActivity(), InformationOfUserFragment.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            this.getActivity().finish();

            activity = (MainActivity) getActivity();
            InformationOfUserFragment f = new InformationOfUserFragment();
            activity.replaceFragment(f);

        } catch (Exception e) {
            Log.d("fail", "" + "fail");
            e.printStackTrace();
        }

    }

    private void saveInfo() {
        try {
            Log.d("sherf_1", "called" + useremail);
            if (!useremail.isEmpty()) {
                Log.d("sherf", "called" + useremail);
                sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString(MSSV, userMSSV);
                editor.putString(NAME, username);
                editor.putString(EMAIL, useremail);
                editor.putString(PASSWORD, userpass);
                editor.putInt(String.valueOf(GIOITINH), usergt);
                editor.putString(NGAYSINH, userns);

                Log.d("userns", "" + userns);

                editor.apply();

            } else {
                //ye ata toast
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}