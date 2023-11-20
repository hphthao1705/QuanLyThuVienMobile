package com.example.qlthuvien.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.SinhVien;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.respository.SinhVienRepository;
import com.example.qlthuvien.viewmodels.SinhVienViewModel;

import com.example.qlthuvien.R;
import com.google.android.material.snackbar.Snackbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    private EditText tensv, et_email, etpassword, mssv_u, txt_repassword;
    private RadioButton gtNam, gtNu, gtinh;
    private RadioGroup radioGroup;
    private Button btnregister;
    private TextView tvlogin;

    private SinhVienViewModel sinhVienViewModel;

    private String currentMssv = "";

    int gioitinhNam;
    int gioitinhNu;
    String sdt, str_userid;
    int id_dg, id_sv;
    String email, repass;
    String name, mssv;
    int gt;
    String password;
    LinearLayout lyt_linear,ll_lay;
    RelativeLayout rl_pwd, rl_name, rl_mssv, rl_email, rl_repass;
    Pattern pattern_pwd = Pattern.compile("^[a-zA-Z0-9]+$");

    public RegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_register);

        mssv_u = (EditText) findViewById(R.id.txt_mssv);

        tensv = (EditText) findViewById(R.id.txt_name);


        et_email = (EditText) findViewById(R.id.txt_email);
        etpassword = (EditText) findViewById(R.id.txt_password);
        txt_repassword = (EditText) findViewById(R.id.txt_repassword);

        btnregister = findViewById(R.id.btn_dangky);

        lyt_linear = findViewById(R.id.lyt_linear);
        ll_lay = findViewById(R.id.ll_lay);
        tvlogin = (TextView) findViewById(R.id.tvlogin);
        rl_pwd = findViewById(R.id.rl_pwd);
        rl_email = findViewById(R.id.rl_email);
        rl_mssv = findViewById(R.id.rl_mssv);
        rl_name = findViewById(R.id.rl_name);
        rl_repass = findViewById(R.id.rl_repass);

        Log.d("email", "" + email);

        name = tensv.getText().toString();

        sinhVienViewModel = ViewModelProviders.of(this).get(SinhVienViewModel.class);


        mssv_u.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String currentMssv = charSequence.toString();
                if (!currentMssv.isEmpty()) {
                    LiveData<SinhVien> tenSinhVienLiveData = sinhVienViewModel.generateTenSinhVien(currentMssv);
                    tenSinhVienLiveData.observe(RegisterActivity.this, new Observer<SinhVien>() {
                        @Override
                        public void onChanged(SinhVien sinhVien) {
                            tensv.setText(sinhVien.getTensv());
                            id_sv = sinhVien.getId_sv();
                        }
                    });
                } else {
                    tensv.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mssv = mssv_u.getText().toString();
                email = et_email.getText().toString();
                password = etpassword.getText().toString();
                repass = txt_repassword.getText().toString();

                Log.d("password", "" + password);
                Log.d("repass", "" + repass);

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {
                        if (!repass.isEmpty() && pattern_pwd.matcher(repass).matches()) {
                            if (password.equals(repass)) {
                                sinhVienViewModel.checkAccountExistence(email).observe(RegisterActivity.this, new Observer<Boolean>() {
                                    @Override
                                    public void onChanged(Boolean accountExists) {
                                        if (accountExists) {
                                            Snackbar.make(ll_lay, "Email already exists", Snackbar.LENGTH_SHORT).show();
                                        } else {
                                            sinhVienViewModel.checkMssvExistence(mssv).observe(RegisterActivity.this, new Observer<Boolean>() {
                                                @Override
                                                public void onChanged(Boolean mssvExists) {
                                                    if (mssvExists) {
                                                        DocGia docGia = new DocGia(0, id_sv, email, password);
                                                        LiveData<DocGia> docGiaLiveData = sinhVienViewModel.registerDocGia(docGia);
                                                        docGiaLiveData.observe(RegisterActivity.this, new Observer<DocGia>() {
                                                            @Override
                                                            public void onChanged(DocGia docGia) {
                                                                Log.d("email", "success: " + email);
                                                                if (docGia != null) {
                                                                    Log.d("test", "success: " + "Đăng ký Độc giả thành công!");
                                                                } else {
                                                                    Log.d("test", "Fail!: " + "Đăng ký Độc giả thất bại!");
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        // MSSV doesn't exist, show appropriate message or take necessary action
                                                        Snackbar.make(ll_lay, "MSSV not found", Snackbar.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            } else {
                                Snackbar.make(rl_repass, "Password and Re-Password not alike", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(rl_repass, "Enter the Valid Re-Password", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Snackbar.make(rl_pwd, "Enter the Valid Password", Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(ll_lay, "Enter the Valid Email", Snackbar.LENGTH_SHORT).show();
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

}