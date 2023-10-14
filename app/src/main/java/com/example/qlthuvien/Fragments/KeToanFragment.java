package com.example.qlthuvien.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qlthuvien.Adapters.LoadAllAdapter;
import com.example.qlthuvien.Class.TaiLieu;
import com.example.qlthuvien.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KeToanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeToanFragment extends Fragment {
    String tendg;
    Integer madg;

    public KeToanFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }

    private ArrayList<TaiLieu> newArrayList = new ArrayList<>();
    private RecyclerView recycleView;
    private LoadAllAdapter adapter;
    private GridLayoutManager layout;

    public KeToanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KeToanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeToanFragment newInstance(String param1, String param2) {
        KeToanFragment fragment = new KeToanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ke_toan, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView = view.findViewById(R.id.rec_allbook);

        layout = new GridLayoutManager(getContext(), 3);
        recycleView = view.findViewById(R.id.rec_ketoan);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layout);
//        adapter = new LoadAllAdapter(newArrayList,tendg,madg);
//        adapter.notifyDataSetChanged();




    }

}