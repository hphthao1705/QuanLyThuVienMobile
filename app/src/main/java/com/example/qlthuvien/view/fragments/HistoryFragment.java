package com.example.qlthuvien.view.fragments;

import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.databinding.FragmentHistoryBinding;
import com.example.qlthuvien.databinding.FragmentInformationBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.BookInTopAdapter;
import com.example.qlthuvien.view.adapter.HistoryAdapter;
import com.example.qlthuvien.viewmodels.MuonTraViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    FragmentHistoryBinding binding;
    MainActivity activity;
    private MuonTraViewModel viewModel;
    HistoryAdapter adapter = new HistoryAdapter(new ArrayList<>());
    ArrayList<MuonTra> list = new ArrayList<>();
    int id_dg = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadId_dg();
        viewModel = new ViewModelProvider(this).get(MuonTraViewModel.class);

        viewModel.loadListOfBorrowBook();

        loadHistory();

        initRecyclerView();
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

    }

    private void loadHistory()
    {
        binding.btnRcvLichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<List<MuonTra>>() {
                    @Override
                    public void onChanged(List<MuonTra> muonTras) {
                        Toast.makeText(getContext(), muonTras.size() + "", Toast.LENGTH_SHORT).show();
                        for(MuonTra i: muonTras) {
                            if (i.getId_dg() == id_dg) {
                               list.add(i);
                            }
                        }
                        adapter = new HistoryAdapter(list);
                        binding.frameHistory.setAdapter(adapter);
                    }
                });
            }
        });
    }
    private void loadId_dg()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        id_dg = Integer.parseInt(sharedPreferences.getString("user_id", "0"));
    }
    private void initRecyclerView()
    {
        binding.frameHistory.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.frameHistory.setLayoutManager(layoutManager);
    }
}