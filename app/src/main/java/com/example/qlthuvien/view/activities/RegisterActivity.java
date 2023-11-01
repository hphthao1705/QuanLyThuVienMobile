package com.example.qlthuvien.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.example.qlthuvien.data.model.SinhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.R;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.respository.DocGiaRepository;

import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;



public class RegisterActivity extends AppCompatActivity {
    private EditText tensv, et_email, etpassword, mssv_u;
    private RadioButton gtNam, gtNu, gtinh;
    private RadioGroup radioGroup;
    private Button btnregister;
    private TextView tvlogin;
    int gioitinhNam;
    int gioitinhNu;
    String sdt, str_userid;
    int id_dg, id_sv;
    String email, pass;
    String name, mssv, gioitinh, ngaysinh;
    int gt;
    String password;
    LinearLayout lyt_linear;
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

        btnregister = findViewById(R.id.btn_dangky);

        lyt_linear = findViewById(R.id.lyt_linear);
        tvlogin = (TextView) findViewById(R.id.tvlogin);

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mssv = mssv_u.getText().toString();
                name = tensv.getText().toString();

                email = et_email.getText().toString();
                password = etpassword.getText().toString();

                Log.d("userdata", "onClick: " + email + password);
                registerMe();

//                if (!firstName.isEmpty()) {
//                    if (!lastName.isEmpty()) {
//                        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//
//                            if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {
//
//                            } else {
//                                Snackbar.make(lyt_linear, "Enter the Valid Password", Snackbar.LENGTH_SHORT).show();
//
//                            }
//                        } else {
//                            Snackbar.make(lyt_linear, "Enter the Valid Email", Snackbar.LENGTH_SHORT).show();
//
//                        }
//                    } else {
//                        Snackbar.make(lyt_linear, "Enter the Valid Last name", Snackbar.LENGTH_SHORT).show();
//
//                    }
//                } else {
//                    Snackbar.make(lyt_linear, "Enter the Valid First name", Snackbar.LENGTH_SHORT).show();
//
//                }

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

    private void registerMe() {
        APIService api = Common.apiService;

//        DocGia docGiaToResigter = new DocGia();
//        docGiaToResigter.setId_dg(Integer.parseInt(id_dg));
//        docGiaToResigter.setId_sv(Integer.parseInt(id_sv));
//        docGiaToResigter.setEmail(email);
//        docGiaToResigter.setPassword(pass);
//
//        SinhVien sinhVienResigter = new SinhVien();
//        sinhVienResigter.setId_sv(Integer.parseInt(id_sv));
//        sinhVienResigter.setMssv(mssv);
//        sinhVienResigter.setTensv(name);
//        sinhVienResigter.setGioitinh(gioitinh);
//        sinhVienResigter.setNgaysinh(ngaysinh);
//
//        Call<SinhVien> call = api.getUserRegi(name, gt);
//        Call<DocGia> callDocGia = api.getUserRegiDG(docGiaToResigter);
//
//        call.enqueue(new Callback<SinhVien>() {
//            @Override
//            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
//                Log.i("Responsestring", response.body().toString());
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        Log.d("responseLog", response.body().getTensv());
//                        Log.i("onSuccess", response.body().toString());
//
//                        response.body().getTensv();
//
//                    } else {
//                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SinhVien> call, Throwable t) {
//                Log.d("go", "onFailure: " + t.toString());
//            }
//        });
//
//        callDocGia.enqueue(new Callback<DocGia>() {
//            @Override
//            public void onResponse(Call<DocGia> call, Response<DocGia> response) {
//                Log.i("Responsestring", response.body().toString());
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        Log.d("responseLog", response.body().getEmail());
//                        Log.i("onSuccess", response.body().toString());
//
//                        String jsonresponse = response.body().getEmail();
//                        try {
//                            parseRegData(jsonresponse);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DocGia> call, Throwable t) {
//                Log.d("go", "onFailure: " + t.toString());
//            }
//        });
    }


    private void parseRegData(String response) throws JSONException {
        Log.d("juststring", response);
        if (response.equals("success")) {
            Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this, "OOPS", Toast.LENGTH_SHORT).show();
        }
    }

}