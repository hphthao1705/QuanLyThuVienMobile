package com.example.qlthuvien.view.fragments;

import static com.example.qlthuvien.view.activities.LoginActivity.ID_DG;
import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qlthuvien.data.local.entities.Cart;
import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.databinding.FragmentCartBookBinding;
import com.example.qlthuvien.view.adapter.CartBookAdapter;
import com.example.qlthuvien.dto.DtoFavourite;
import com.example.qlthuvien.R;
import com.example.qlthuvien.viewmodels.CartViewModel;
import com.example.qlthuvien.viewmodels.ChiTietMuonTraViewModel;
import com.example.qlthuvien.viewmodels.MuonTraViewModel;
import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.google.gson.JsonParser;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartBookFragment extends Fragment {
    MutableLiveData<String> thaoQuaMetRoiHuHu = new MutableLiveData<>();
    FragmentCartBookBinding binding;
    private CartViewModel viewModel;
    List<DtoFavourite> list = new ArrayList<>();
    List<Cart> cartList = new ArrayList<>();
    CartBookAdapter adapter = new CartBookAdapter(getContext());
    ArrayList<MuonTra> list_thao = new ArrayList<>();
    private MuonTraViewModel muonTraViewModel;
    private ChiTietMuonTraViewModel chiTietMuonTraViewModel;
    int id_dg = 0;
    int count=0;
    int numberOfBooksIsChoosen = 0;
    CartBookFragment.AsyncTask task = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_book, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.progressbarStart.setVisibility(View.VISIBLE);
        loadId_dg();

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        muonTraViewModel = new ViewModelProvider(this).get(MuonTraViewModel.class);
        chiTietMuonTraViewModel = new ViewModelProvider(this).get(ChiTietMuonTraViewModel.class);
        //viewModel = ViewModelProviders.of(this).get(CartViewModel.class);

        viewModel.getAllBookFromUser(id_dg);
        viewModel.countBookWhichIsChoosen(id_dg);
        viewModel.addBooksToCallCard(id_dg);

        loadBorrowBooks();

        loadCart();
        initRecyclerView();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rcvCartBook);
        loadCountBook();
        borrowBooks();
    }
    private void loadCart()
    {
        viewModel.cartBook.observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                list.clear();
                if(carts.size() == 0)
                {
                    loadView(false);
                }
                else
                {
                    for (Cart i:carts) {
                        cartList = carts;
                        DtoFavourite j = new DtoFavourite();
                        j.Id = String.valueOf(i.getId_tailieu());
                        j.Check = i.getCheckbox();
                        j.TacGia = i.getTacgia();
                        j.TenSach = i.getTensach();
                        list.add(j);
                    }
                    adapter.SetData(list);
                    binding.rcvCartBook.setAdapter(adapter);
                    loadView(true);
                    chooseBooks();
                }
                binding.progressbarStart.setVisibility(View.GONE);
            }
        });
    }
    private void initRecyclerView()
    {
        binding.rcvCartBook.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcvCartBook.setLayoutManager(layoutManager);
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction)
            {
                case ItemTouchHelper.LEFT:{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn muốn xóa quyển sách này ra khỏi giỏ sách?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // quang code cua thao vo day
                            Cart cart = new Cart(cartList.get(position).getId_tailieu(), id_dg, cartList.get(position).getHinh(), cartList.get(position).getTensach(),  cartList.get(position).getTacgia(),  cartList.get(position).getCheckbox());
                            viewModel.delete(cart);
                            list.remove(position);

                            adapter.notifyItemChanged(position);
                            // Do nothing, but close the dialog
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.baseline_delete_24)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
    private void chooseBooks()
    {
        adapter.setOnCheckedListener(new CartBookAdapter.OnCheckedChangeListener() {
            @Override
            public void onChecked(boolean checked, CartBookAdapter.CartBookAdapterViewHolder viewHolder) {
                viewModel.countBookWhichIsChoosen(id_dg);
                if(task != null)
                {
                    task.cancel(true);
                    task = null;
                    Log.d("check cart", "asynctask is still working");
                }
                Log.d("check cart", "asynctask is not working when check this checkbox");
                int position = viewHolder.getAdapterPosition();
                if(checked)
                {
                    viewModel.update(1, cartList.get(position).getId_tailieu(), id_dg);
                    Log.d("check cart", "check");
                }
                else
                {
                    viewModel.update(0, cartList.get(position).getId_tailieu(), id_dg);
                    Log.d("check cart", "uncheck");
                }
            }
        });
    }
    public void loadCountBook()
    {
        viewModel.count.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                numberOfBooksIsChoosen = integer;
                binding.txtSoLuong.setText("Số lượng: " + numberOfBooksIsChoosen);
            }
        });
    }
    private void borrowBooks()
    {
        binding.btnMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                if(numberOfBooksIsChoosen == 0)
                {
                    builder.setMessage("Vui lòng chọn sách mà bạn muốn mượn");
                }
                else {
                    if(count + numberOfBooksIsChoosen > 3)
                    {

                        builder.setTitle("Thông báo");
                        if((3 - count) <= 0)
                        {
                            builder.setMessage("Số sách bạn muốn mượn đã vượt quá quy định. Hãy trả sách để có thể mượn tiếp");
                        }
                        else {
                            builder.setMessage("Số sách bạn muốn mượn đã vượt quá quy định. Bạn chỉ có thể mượn " + (3 - count) + " quyển sách");
                        }
                    }
                    else
                    {
                        Log.d("check cart", "start asynctask");
                        //add phiếu mượn
                        task = (CartBookFragment.AsyncTask) new CartBookFragment.AsyncTask().execute();

                    }
                }
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    private void addDetailOfCallCard()
    {
        //add chi tiết phiếu mượn
        muonTraViewModel.liveData.observe(getViewLifecycleOwner(), new Observer<List<MuonTra>>() {
            @Override
            public void onChanged(List<MuonTra> muonTras) {
                Collections.reverse(muonTras);
                viewModel.listLiveData.observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
                    @Override
                    public void onChanged(List<Cart> carts) {
                        int j = 0;
                        Log.d("id_muon", String.valueOf(muonTras.get(0).getId_muon()));
                        Log.d("size_ctmt: ", carts.size() + "");
                        for(Cart i: carts)
                        {
                            String jsonString = "{'id_ctmuon':0,'id_muon':"+(muonTras.get(0).getId_muon() + 1)+",'id_tailieu':"+ i.getId_tailieu() +",'ngaytra':null,'tinhtrangtra':0}";
                            Log.d("json: ",jsonString);

                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString);
                            chiTietMuonTraViewModel.insertCallCard(jsonObject);
                            j++;
                            if(j==carts.size())
                            {
                                thaoQuaMetRoiHuHu.setValue("ok");
                            }
                        }
                    }
                });
            }
        });
        thaoQuaMetRoiHuHu.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(task != null)
                {
                    Log.d("check cart", "delete book which is borrowed");
                    viewModel.deleteBooksWhichIsBorrowed(id_dg);
                    task.cancel(true);
                    Log.d("check cart", "cancel asynctask");
                }
            }
        });
        //task.cancel(true);

    }
    private class AsyncTask extends android.os.AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String d = formatter.format(new Date());
            String jsonString = "{'id_muon':0,'id_dg':" + id_dg + ",'id_nv':1,'ngaymuon': '"+d+"','tintrangmuon':0}";
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString);
            muonTraViewModel.insertCallCard(jsonObject);
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            if(isCancelled())
//            {
//                Log.d("check cart", "cancel asynctask");
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            addDetailOfCallCard();
        }
    }
    private void loadId_dg()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        id_dg = Integer.parseInt(sharedPreferences.getString("user_id", "0"));
    }
    private void loadView(boolean check)
    {
        if(check)
        {
            binding.empty1.setVisibility(View.GONE);
            binding.empty2.setVisibility(View.GONE);
            binding.empty3.setVisibility(View.GONE);
            binding.rcvCartBook.setVisibility(View.VISIBLE);
            binding.muon.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.empty1.setVisibility(View.VISIBLE);
            binding.empty2.setVisibility(View.VISIBLE);
            binding.empty3.setVisibility(View.VISIBLE);
            binding.rcvCartBook.setVisibility(View.GONE);
            binding.muon.setVisibility(View.GONE);
        }
    }
    private void loadBorrowBooks()
    {
        muonTraViewModel.liveData.observe(getViewLifecycleOwner(), new Observer<List<MuonTra>>() {
            @Override
            public void onChanged(List<MuonTra> muonTras) {
                for(MuonTra i:muonTras)
                {
                    if(i.getId_dg() == id_dg)
                    {
                        list_thao.add(i);
                    }
                }
                countBooksThatUserBorrow();
            }
        });
    }
    private void countBooksThatUserBorrow()
    {
        chiTietMuonTraViewModel.liveData.observe(getViewLifecycleOwner(), new Observer<List<ChiTietMuonTra>>() {
            @Override
            public void onChanged(List<ChiTietMuonTra> chiTietMuonTras) {
                for (ChiTietMuonTra i:chiTietMuonTras)
                {
                    for(MuonTra j:list_thao)
                    {
                        if(i.getId_muon() == j.getId_muon() && (i.getTinhtrangtra() == 0 || i.getTinhtrangtra() == 1))
                        {
                            count++;
                        }
                    }
                }
            }
        });
    }
}