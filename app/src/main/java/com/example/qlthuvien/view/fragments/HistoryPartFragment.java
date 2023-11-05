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
import com.example.qlthuvien.databinding.FragmentHistoryPartBinding;
import com.example.qlthuvien.view.adapter.HistoryAdapter;
import com.example.qlthuvien.viewmodels.MuonTraViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryPartFragment extends Fragment {
    int i;
    FragmentHistoryPartBinding binding;
    private MuonTraViewModel viewModel;
    HistoryAdapter adapter = new HistoryAdapter(new ArrayList<>());
    ArrayList<MuonTra> list = new ArrayList<>();
    ArrayList<MuonTra> list_view = new ArrayList<>();
    int id_dg = 0;
    public HistoryPartFragment(int i)
    {
        this.i = i;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history_part, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.progressbarStart.setVisibility(View.VISIBLE);
        binding.frameHistory.setVisibility(View.GONE);
        viewModel = new ViewModelProvider(this).get(MuonTraViewModel.class);
        loadId_dg();
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<List<MuonTra>>() {
            @Override
            public void onChanged(List<MuonTra> muonTras) {
                list.clear();
                for(MuonTra i: muonTras)
                {
                    if(i.getId_dg() == id_dg)
                    {
                        list.add(i);
                    }
                }
                if(i == 0)
                {
                    list_view.clear();
                    list_view = (ArrayList<MuonTra>) list.stream().filter(p -> p.getTintrangmuon() != 0).collect(Collectors.toList());
                }
                else {
                    list_view.clear();
                    list_view = (ArrayList<MuonTra>) list.stream().filter(p -> p.getTintrangmuon() == 0).collect(Collectors.toList());
                }
                adapter = new HistoryAdapter((ArrayList<MuonTra>) list_view);
                binding.frameHistory.setAdapter(adapter);
                binding.frameHistory.setVisibility(View.VISIBLE);
                binding.progressbarStart.setVisibility(View.GONE);
            }
        });

        initRecyclerView();
    }
    private void initRecyclerView()
    {
        binding.frameHistory.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.frameHistory.setLayoutManager(layoutManager);
    }
    private void loadId_dg()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        id_dg = Integer.parseInt(sharedPreferences.getString("user_id", "0"));
    }
}