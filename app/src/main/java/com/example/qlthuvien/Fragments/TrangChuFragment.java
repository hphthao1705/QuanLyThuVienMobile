package com.example.qlthuvien.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.qlthuvien.Activities.Activity_TimKiemSach;
import com.example.qlthuvien.Adapters.CustomGridAdapter_TrangChu;
import com.example.qlthuvien.Class.TaiLieu;
import com.example.qlthuvien.R;
import com.example.qlthuvien.Activities.TheLoai;

import java.util.ArrayList;
import java.util.List;


public class TrangChuFragment extends Fragment {
    String tendg;
    Integer madg;

    public TrangChuFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }

    List<TaiLieu> mlistTaiLieu;
    CardView cv_TinHoc, cv_NgoaiNgu, cv_KeToan, cv_SinhHoc, cv_DetMay, cv_DuLich;
    private RecyclerView recyclerView;
    private Context context;
    private CustomGridAdapter_TrangChu customGridAdapter_trangChu;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        recyclerView = rootView.findViewById(R.id.rec_trangchu);
        searchView = rootView.findViewById(R.id.searchView);
        searchView.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.setAdapter(customGridAdapter_trangChu);
        mlistTaiLieu = new ArrayList<>();
        customGridAdapter_trangChu = new CustomGridAdapter_TrangChu(mlistTaiLieu);
        recyclerView.setAdapter(customGridAdapter_trangChu);

        System.out.println("Result "+madg);


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent searchIntent=new Intent(getActivity(), Activity_TimKiemSach.class);
                getActivity().startActivity(searchIntent);
            }
        });
        cv_TinHoc = rootView.findViewById(R.id.cardView_TinHoc);
        cv_NgoaiNgu = rootView.findViewById(R.id.cardView_NgoaiNgu);
        cv_KeToan = rootView.findViewById(R.id.cardView_KeToan);
        cv_SinhHoc = rootView.findViewById(R.id.cardView_SinhHoc);
        cv_DetMay = rootView.findViewById(R.id.cardView_DetMay);
        cv_DuLich = rootView.findViewById(R.id.cardView_DuLich);

        cv_TinHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
//                intent.putExtra("number","1");
                intent.putExtra("madg",""+madg);
                System.out.println("t mệt quá :( "+madg);

                getActivity().startActivity(intent);

            }
        });
        cv_NgoaiNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
//                intent.putExtra("number","2");
                intent.putExtra("madg",""+madg);
                getActivity().startActivity(intent);
            }
        });
        cv_KeToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
//                intent.putExtra("number","3");
                intent.putExtra("madg",""+madg);
                getActivity().startActivity(intent);
            }
        });
        cv_SinhHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
//                intent.putExtra("number","4");
                intent.putExtra("madg",""+madg);
                getActivity().startActivity(intent);
            }
        });
        cv_DetMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
//                intent.putExtra("number","5");
                intent.putExtra("madg",""+madg);
                getActivity().startActivity(intent);
            }
        });
        cv_DuLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
//                intent.putExtra("number","6");
                intent.putExtra("madg",""+madg);
                getActivity().startActivity(intent);
            }
        });

        return rootView;


    }


}