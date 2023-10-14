package com.example.qlthuvien.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlthuvien.Adapters.LoadAllAdapter;

import com.example.qlthuvien.Class.TaiLieu;
import com.example.qlthuvien.R;
//import com.example.qlthuvien.Activities.TheLoai;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {
    String tendg;
    Integer madg;

    public AllFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ArrayList<TaiLieu> newArrayList = new ArrayList<>();
    private RecyclerView recycleView;
    private LoadAllAdapter adapter;
    private GridLayoutManager layout;

    // TODO: Rename and change types of parameters
    public AllFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance() {
        AllFragment fragment = new AllFragment();
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
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView = view.findViewById(R.id.rec_allbook);

        layout = new GridLayoutManager(getContext(), 3);
        recycleView = view.findViewById(R.id.rec_allbook);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layout);
//        adapter = new LoadAllAdapter(newArrayList,tendg,madg);
//        adapter.notifyDataSetChanged();



    }


}