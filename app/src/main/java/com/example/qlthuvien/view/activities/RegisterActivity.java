package com.example.qlthuvien.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.SinhVien;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.respository.SinhVienRepository;
import com.example.qlthuvien.view.fragments.InformationFragment;
import com.example.qlthuvien.view.fragments.NavigationBottomFragment;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


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
    private ImageButton btnBack;
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
        btnBack = findViewById(R.id.btn_back);

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
                            if (sinhVien != null) {
                                tensv.setText(sinhVien.getTensv());
                                id_sv = sinhVien.getId_sv();
                            } else {
                                tensv.setText("");
                            }
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


//        btnregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mssv = mssv_u.getText().toString();
//                email = et_email.getText().toString();
//                password = etpassword.getText().toString();
//                repass = txt_repassword.getText().toString();
//
//                Log.d("password", "" + password);
//                Log.d("repass", "" + repass);
//
////                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
////                    if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {
////                        if (!repass.isEmpty() && pattern_pwd.matcher(repass).matches()) {
////                            if (password.equals(repass)) {
//
//                if(!email.isEmpty() && !password.isEmpty() && !repass.isEmpty() && !mssv.isEmpty())
//                {
//                    if(!mssv.isEmpty())
//                    {
//                        sinhVienViewModel.checkAccountExistence(id_sv).observe(RegisterActivity.this, new Observer<Boolean>() {
//                            @Override
//                            public void onChanged(Boolean accountExists) {
//                                if (accountExists) {
//                                    Snackbar.make(ll_lay, "MSSV này đã có tài khoản rồi", Snackbar.LENGTH_SHORT).show();
//                                    mssv_u.setText("");
//                                    tensv.setText("");
//                                    et_email.setText("");
//                                    etpassword.setText("");
//                                    txt_repassword.setText("");
//                                } else {
//
//                                    sinhVienViewModel.checkEmailExistence(email).observe(RegisterActivity.this, new Observer<Boolean>() {
//                                        @Override
//                                        public void onChanged(Boolean aBoolean) {
//                                            if (aBoolean) {
//                                                Snackbar.make(ll_lay, "Email đã tồn tại", Snackbar.LENGTH_SHORT).show();
//                                                mssv_u.setText("");
//                                                tensv.setText("");
//                                                et_email.setText("");
//                                                etpassword.setText("");
//                                                txt_repassword.setText("");
//                                            } else {
//                                                sinhVienViewModel.checkMssvExistence(mssv).observe(RegisterActivity.this, new Observer<Boolean>() {
//                                                    @Override
//                                                    public void onChanged(Boolean mssvExists) {
//                                                        if (mssvExists) {
//
//                                                            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                                                                if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {
//                                                                    if (!repass.isEmpty() && pattern_pwd.matcher(repass).matches()) {
//                                                                        if (password.equals(repass)) {
//
//                                                                            DocGia docGia = new DocGia(0, id_sv, email, password);
//                                                                            LiveData<DocGia> docGiaLiveData = sinhVienViewModel.registerDocGia(docGia);
//                                                                            docGiaLiveData.observe(RegisterActivity.this, new Observer<DocGia>() {
//                                                                                @Override
//                                                                                public void onChanged(DocGia docGia) {
//                                                                                    Log.d("email", "success: " + email);
//                                                                                    if (docGia != null) {
//                                                                                        Log.d("test", "success: " + "Đăng ký Độc giả thành công!");
//                                                                                        parseRegisterData();
//                                                                                    } else {
//                                                                                        Log.d("test", "Fail!: " + "Đăng ký Độc giả thất bại!");
//                                                                                    }
//                                                                                }
//                                                                            });
//                                                                        } else {
//                                                                            Snackbar.make(rl_repass, "Mật khẩu và nhập lại mật khẩu không trùng", Snackbar.LENGTH_SHORT).show();
//                                                                            mssv_u.setText("");
//                                                                            tensv.setText("");
//                                                                            et_email.setText("");
//                                                                            etpassword.setText("");
//                                                                            txt_repassword.setText("");
//                                                                        }
//                                                                    } else {
//                                                                        Snackbar.make(rl_repass, "Nhập lại mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
//                                                                        mssv_u.setText("");
//                                                                        tensv.setText("");
//                                                                        et_email.setText("");
//                                                                        etpassword.setText("");
//                                                                        txt_repassword.setText("");
//                                                                    }
//                                                                } else {
//                                                                    Snackbar.make(rl_pwd, "Mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
//                                                                    mssv_u.setText("");
//                                                                    tensv.setText("");
//                                                                    et_email.setText("");
//                                                                    etpassword.setText("");
//                                                                    txt_repassword.setText("");
//                                                                }
//                                                            } else {
//                                                                Snackbar.make(ll_lay, "Email trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
//                                                                et_email.setText("");
//                                                            }
//
//                                                        } else {
//                                                            // MSSV doesn't exist, show appropriate message or take necessary action
//                                                            Snackbar.make(ll_lay, "Không tìm thấy MSSV", Snackbar.LENGTH_SHORT).show();
//
//                                                            mssv_u.setText("");
//                                                            tensv.setText("");
//                                                            et_email.setText("");
//                                                            etpassword.setText("");
//                                                            txt_repassword.setText("");
//                                                        }
//                                                    }
//                                                });
//                                            }
//                                        }
//                                    });
//                                }
//                            }
//                        });
//                    }
//                    else
//                    {
//                        Snackbar.make(ll_lay, "Vui lòng nhập MSSV", Snackbar.LENGTH_SHORT).show();
//                    }
//                }
//                else
//                {
//                    Snackbar.make(ll_lay, "Vui lòng nhập đầy đủ thông tin", Snackbar.LENGTH_SHORT).show();
//                }
////                            } else {
////                                Snackbar.make(rl_repass, "Mật khẩu và nhập lại mật khẩu không trùng", Snackbar.LENGTH_SHORT).show();
////                                mssv_u.setText("");
////                                tensv.setText("");
////                                et_email.setText("");
////                                etpassword.setText("");
////                                txt_repassword.setText("");
////                            }
////                        } else {
////                            Snackbar.make(rl_repass, "Nhập lại mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
////                            mssv_u.setText("");
////                            tensv.setText("");
////                            et_email.setText("");
////                            etpassword.setText("");
////                            txt_repassword.setText("");
////                        }
////                    } else {
////                        Snackbar.make(rl_pwd, "Mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
////                        mssv_u.setText("");
////                        tensv.setText("");
////                        et_email.setText("");
////                        etpassword.setText("");
////                        txt_repassword.setText("");
////                    }
////                } else {
////                    Snackbar.make(ll_lay, "Email trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
////                    et_email.setText("");
////                }
//            }
//        });






        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mssv = mssv_u.getText().toString();
                email = et_email.getText().toString();
                password = etpassword.getText().toString();
                repass = txt_repassword.getText().toString();

                Log.d("password", "" + password);
                Log.d("repass", "" + repass);

//                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {
//                        if (!repass.isEmpty() && pattern_pwd.matcher(repass).matches()) {
//                            if (password.equals(repass)) {

                if(!email.isEmpty() && !password.isEmpty() && !repass.isEmpty() && !mssv.isEmpty())
                {
                    if(!mssv.isEmpty())
                    {

                        sinhVienViewModel.checkMssvExistence(mssv).observe(RegisterActivity.this, new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean mssvExists) {
                                if (!mssvExists) {
                                    Snackbar.make(ll_lay, "Không tìm thấy MSSV", Snackbar.LENGTH_SHORT).show();
                                    mssv_u.setText("");
                                    tensv.setText("");
                                    et_email.setText("");
                                    etpassword.setText("");
                                    txt_repassword.setText("");
                                }
                                else
                                {
                                    sinhVienViewModel.checkEmailExistence(email).observe(RegisterActivity.this, new Observer<Boolean>() {
                                        @Override
                                        public void onChanged(Boolean aBoolean) {
                                            if (aBoolean) {
                                                Snackbar.make(ll_lay, "Email đã tồn tại", Snackbar.LENGTH_SHORT).show();
                                                mssv_u.setText("");
                                                tensv.setText("");
                                                et_email.setText("");
                                                etpassword.setText("");
                                                txt_repassword.setText("");
                                            } else {
                                                sinhVienViewModel.checkAccountExistence(id_sv).observe(RegisterActivity.this, new Observer<Boolean>() {
                                                    @Override
                                                    public void onChanged(Boolean accountExists) {
                                                        if (accountExists) {
                                                            Snackbar.make(ll_lay, "MSSV này đã có tài khoản rồi", Snackbar.LENGTH_SHORT).show();
                                                            mssv_u.setText("");
                                                            tensv.setText("");
                                                            et_email.setText("");
                                                            etpassword.setText("");
                                                            txt_repassword.setText("");
                                                        }
                                                        else {
                                                            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                                                if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {
                                                                    if (!repass.isEmpty() && pattern_pwd.matcher(repass).matches()) {
                                                                        if (password.equals(repass)) {

                                                                            DocGia docGia = new DocGia(0, id_sv, email, password);
                                                                            LiveData<DocGia> docGiaLiveData = sinhVienViewModel.registerDocGia(docGia);
                                                                            docGiaLiveData.observe(RegisterActivity.this, new Observer<DocGia>() {
                                                                                @Override
                                                                                public void onChanged(DocGia docGia) {
                                                                                    Log.d("email", "success: " + email);
                                                                                    if (docGia != null) {
                                                                                        Log.d("test", "success: " + "Đăng ký Độc giả thành công!");
                                                                                        parseRegisterData();
                                                                                    } else {
                                                                                        Log.d("test", "Fail!: " + "Đăng ký Độc giả thất bại!");
                                                                                    }
                                                                                }
                                                                            });
                                                                        } else {
                                                                            Snackbar.make(rl_repass, "Mật khẩu và nhập lại mật khẩu không trùng", Snackbar.LENGTH_SHORT).show();
                                                                            mssv_u.setText("");
                                                                            tensv.setText("");
                                                                            et_email.setText("");
                                                                            etpassword.setText("");
                                                                            txt_repassword.setText("");
                                                                        }
                                                                    } else {
                                                                        Snackbar.make(rl_repass, "Nhập lại mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
                                                                        mssv_u.setText("");
                                                                        tensv.setText("");
                                                                        et_email.setText("");
                                                                        etpassword.setText("");
                                                                        txt_repassword.setText("");
                                                                    }
                                                                } else {
                                                                    Snackbar.make(rl_pwd, "Mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
                                                                    mssv_u.setText("");
                                                                    tensv.setText("");
                                                                    et_email.setText("");
                                                                    etpassword.setText("");
                                                                    txt_repassword.setText("");
                                                                }
                                                            } else {
                                                                Snackbar.make(ll_lay, "Email trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
                                                                et_email.setText("");
                                                            }

                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                    else
                    {
                        Snackbar.make(ll_lay, "Vui lòng nhập MSSV", Snackbar.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Snackbar.make(ll_lay, "Vui lòng nhập đầy đủ thông tin", Snackbar.LENGTH_SHORT).show();
                }
//                            } else {
//                                Snackbar.make(rl_repass, "Mật khẩu và nhập lại mật khẩu không trùng", Snackbar.LENGTH_SHORT).show();
//                                mssv_u.setText("");
//                                tensv.setText("");
//                                et_email.setText("");
//                                etpassword.setText("");
//                                txt_repassword.setText("");
//                            }
//                        } else {
//                            Snackbar.make(rl_repass, "Nhập lại mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
//                            mssv_u.setText("");
//                            tensv.setText("");
//                            et_email.setText("");
//                            etpassword.setText("");
//                            txt_repassword.setText("");
//                        }
//                    } else {
//                        Snackbar.make(rl_pwd, "Mật khẩu trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
//                        mssv_u.setText("");
//                        tensv.setText("");
//                        et_email.setText("");
//                        etpassword.setText("");
//                        txt_repassword.setText("");
//                    }
//                } else {
//                    Snackbar.make(ll_lay, "Email trống hoặc sai ký tự", Snackbar.LENGTH_SHORT).show();
//                    et_email.setText("");
//                }
            }
        });
    }

    private void parseRegisterData() {
        try {
            Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
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