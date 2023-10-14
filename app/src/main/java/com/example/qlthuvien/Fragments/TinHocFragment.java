package com.example.qlthuvien.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.qlthuvien.Adapters.LoadAllAdapter;
import com.example.qlthuvien.Class.TaiLieu;
import com.example.qlthuvien.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TinHocFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TinHocFragment extends Fragment {
    String tendg;
    Integer madg;

    public TinHocFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<TaiLieu> newArrayList = new ArrayList<>();
    private RecyclerView recycleView;
    private LoadAllAdapter adapter;
    private GridLayoutManager layout;

    public TinHocFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TinHocFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TinHocFragment newInstance(String param1, String param2) {
        TinHocFragment fragment = new TinHocFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tin_hoc, container, false);
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