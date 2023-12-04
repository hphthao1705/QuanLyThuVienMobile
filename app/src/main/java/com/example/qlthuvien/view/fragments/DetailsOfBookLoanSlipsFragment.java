package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.ChiTietMuonTra_Full;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.databinding.FragmentDetailsOfBookLoanSlipsBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.DetailsCallCardAdapter;
import com.example.qlthuvien.view.adapter.HistoryAdapter;
import com.example.qlthuvien.viewmodels.ChiTietMuonTraViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsOfBookLoanSlipsFragment extends Fragment {
    FragmentDetailsOfBookLoanSlipsBinding binding;
    ChiTietMuonTraViewModel viewModel;
    ArrayList<ChiTietMuonTra_Full> list = new ArrayList<>();
    DetailsCallCardAdapter adapter = new DetailsCallCardAdapter(new ArrayList<>(), getContext());
    int id_muon;
    String ngaymuon;
    MainActivity activity;
    int position;
    public DetailsOfBookLoanSlipsFragment(int id_muon, String ngaymuon, int position)
    {
        this.id_muon = id_muon;
        this.ngaymuon = ngaymuon;
        this.position = position;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_of_book_loan_slips, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel =  new ViewModelProvider(this).get(ChiTietMuonTraViewModel.class);

        if(id_muon < 10)
        {
            binding.txtMamuon.setText("PM00" + id_muon);
        }
        else {
            binding.txtMamuon.setText("PM0" + id_muon);
        }

        binding.txtNgaymuon.setText("Ngày mượn: " + ngaymuon);

        loadDetail();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                HistoryFragment f = new HistoryFragment(position);
                activity.replaceFragment(f);
            }
        });
    }
    private void loadDetail()
    {
        viewModel.liveData2.observe(getViewLifecycleOwner(), new Observer<List<ChiTietMuonTra_Full>>() {
            @Override
            public void onChanged(List<ChiTietMuonTra_Full> chiTietMuonTraFulls) {
                for (ChiTietMuonTra_Full j: chiTietMuonTraFulls)
                {
                    if(j.getId_muon() == id_muon)
                    {
                        list.add(j);
                    }
                }
                adapter = new DetailsCallCardAdapter(list,getContext());
                binding.frameDetailsOfBookLoanSlips.setAdapter(adapter);
            }
        });
        initRecyclerView();
    }
    private void initRecyclerView()
    {
        binding.frameDetailsOfBookLoanSlips.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.frameDetailsOfBookLoanSlips.setLayoutManager(layoutManager);
    }
}