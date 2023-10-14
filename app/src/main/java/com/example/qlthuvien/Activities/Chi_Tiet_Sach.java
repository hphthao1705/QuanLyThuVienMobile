package com.example.qlthuvien.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.qlthuvien.Class.TaiLieu;

import com.example.qlthuvien.Fragments.TrangChuFragment;
import com.example.qlthuvien.R;


import java.util.ArrayList;
import java.util.List;

public class Chi_Tiet_Sach extends AppCompatActivity {
    ImageView img,ha, img_yt;
    TextView name, tv_tg , theloai1, theloai2, sl, mota, tv_nxb, tv_namxb;
    Button btn_muon;
    String id="";
    Integer madg = 0;
    Integer hinh;
    Integer i=0;
    Integer j=0;
    Integer tt1=0;
    String username;
    List<TaiLieu> taiLieuList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chi_tiet_sach);
        getSupportActionBar().hide();
        addControls();
        taiLieuList = new ArrayList<>();
        img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String back = getIntent().getStringExtra("back_theloai");
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), TrangChuFragment.class);
                if (back != null)
                {
                    intent.setClass(getApplicationContext(), TheLoai.class);
                }
                intent.putExtra("number","10");
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        SharedPreferences sharedPreferences = getSharedPreferences("tk_mk", MODE_PRIVATE);
        username = sharedPreferences.getString("Username", "");
        System.out.println("tên t nè"+username);


    }


    public void addControls()
    {
        img_yt = (ImageView) findViewById(R.id.img_yeuthich);
        name = (TextView) findViewById(R.id.textView11);
        tv_tg = (TextView) findViewById(R.id.tv_tg);
        theloai1 = (TextView) findViewById(R.id.textView7);
        theloai2 = (TextView) findViewById(R.id.textView10);
        sl = (TextView) findViewById(R.id.textView12);
        mota = (TextView) findViewById(R.id.textView13);
        tv_nxb = (TextView) findViewById(R.id.tv_nxb);
        tv_namxb = (TextView) findViewById(R.id.tv_namxb);
        btn_muon = (Button) findViewById(R.id.button2);
        ha = (ImageView) findViewById(R.id.imageView7);
    }




}
