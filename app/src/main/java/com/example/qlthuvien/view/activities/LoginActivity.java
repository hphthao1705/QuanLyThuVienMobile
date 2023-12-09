package com.example.qlthuvien.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.SinhVien;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.R;
import com.example.qlthuvien.data.remote.APIService;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText etUname, etPass;
    private Button btnlogin;
    private TextView tvreg;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String SHARED_PREFERENCES_NAME = "tk_mk";
    public static final String USER_ID = "user_id";
    public static final String NGAYSINH = "ngaysinh";
    public static final String ID_DG = "user_id";
    public static final String ID_DG1 = "";
    public static final String ID_SV = "";
    public static final String MSSV = "mssv";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final int GIOITINH = 0;

    private View parent_view;
    String email, password;
    RelativeLayout rl_pwd;
    LinearLayout ll_lay;
    Pattern pattern_pwd = Pattern.compile("^[a-zA-Z0-9]+$");
    public static String userMSSV = "",  userid = "", username = "" ,useremail = "", userpass = "", userid_dg = "", userid_sv = "", userngaysinh = "";
    public static int usergt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        final boolean firstStart = prefs.getBoolean("firstStart", true);


        ll_lay = findViewById(R.id.ll_lay);
        rl_pwd = findViewById(R.id.rl_pwd);
        etUname = (EditText) findViewById(R.id.txt_username);
        etPass = (EditText) findViewById(R.id.txt_password);
        btnlogin = (Button) findViewById(R.id.btn_dangnhap);
        tvreg = (TextView) findViewById(R.id.tvreg);

        tvreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etUname.getText().toString().trim();
                password = etPass.getText().toString().trim();

                Log.d("userdata", "onClick: " + email + password);

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {

                        loginUser();

                    } else {
                        Snackbar.make(rl_pwd, "Mật khẩu trống hoặc sai định dạng", Snackbar.LENGTH_SHORT).show();
                        etUname.setText("");
                        etPass.setText("");
                    }
                } else {
                    Snackbar.make(ll_lay, "Tài khoản hoặc sai tài khoản", Snackbar.LENGTH_SHORT).show();
                    etUname.setText("");
                    etPass.setText("");
                }

            }
        });
    }

    protected void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.ic_gradient_bg);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    private void loginUser() {
        APIService api = Common.apiService;

//        API service = RetrofitClient.getRetrofitInstance().create(API.class);
        Log.d("call_call", "onClick: " + email + password);
        Call<DocGia> call = api.getUserLogin(email, password);

        call.enqueue(new Callback<DocGia>() {
            @Override
            public void onResponse(Call<DocGia> call, Response<DocGia> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Log.i("onSuccess", response.body().getEmail());
                        userid_dg = String.valueOf(response.body().getId_dg());
                        userid_sv = String.valueOf(response.body().getId_sv());
                        useremail = response.body().getEmail();


                        Common.apiService.getSinhVien(Integer.parseInt(userid_sv)).enqueue(new Callback<SinhVien>() {
                            @Override
                            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {

                                        userMSSV = response.body().getMssv();
                                        username = response.body().getTensv();
                                        userngaysinh = response.body().getNgaysinh();
                                        usergt = response.body().getGioitinh();




                                        try {
                                            parseLoginData();
                                        } catch (Exception e) {
                                            etUname.setText("");
                                            etPass.setText("");
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Log.i("onEmptyResponse", "Tài khoản không tồn tại");
                                        etUname.setText("");
                                        etPass.setText("");
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<SinhVien> call, Throwable t) {
                                Log.d("LoginBHai_error", "onFailure: " + "Throw" + t.toString());
                                etUname.setText("");
                                etPass.setText("");
                                Snackbar.make(rl_pwd, "login error", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Log.i("onEmptyResponse", "Tài khoản không tồn tại");
                        etUname.setText("");
                        etPass.setText("");
                        Snackbar.make(rl_pwd, "Tài khoản không tồn tại", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DocGia> call, Throwable t) {
                Log.d("LoginBHai_error", "onFailure: " + "Throw" + t.toString());
                etUname.setText("");
                etPass.setText("");
                Snackbar.make(rl_pwd, "login error", Snackbar.LENGTH_SHORT).show();
            }
        });

        Log.d("mssv", "called" + userMSSV);

        Log.d("tensv", "called" + username);
    }

    private void parseLoginData() {
        try {
            if (!useremail.isEmpty()) {
                saveInfo();
                Log.d("Docgia", "success");
            } else {
                Log.d("Docgia", "empty");
            }
            Snackbar.make(rl_pwd, "Đăng nhập thành công!", Snackbar.LENGTH_SHORT).show();
            Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();

        } catch (Exception e) {
            etUname.setText("");
            etPass.setText("");
            e.printStackTrace();
        }
    }

    private void saveInfo() {
        try {
            Log.d("sherf_1", "called" + useremail);
            if (!useremail.isEmpty()) {
                Log.d("sherf", "called" + useremail);
                sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString(MSSV, userMSSV);
                editor.putString(NAME, username);

                editor.putString(NGAYSINH, userngaysinh);

                editor.putString(PASSWORD, userpass);
                editor.putString(ID_DG, userid_dg);

                String a = String.valueOf(editor.putString(ID_DG, userid_dg));

                Log.d("test a", "" + a);

                editor.putString(ID_DG1, a);

                editor.putString(ID_SV, userid_sv);

                editor.putInt(String.valueOf(GIOITINH), usergt);
                editor.putString(EMAIL, useremail);

                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                //ye ata toast
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}