package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.YeuThich;
import com.example.qlthuvien.data.remote.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YeuThichRepository {
    public LiveData<List<YeuThich>> loadFavorites()
    {
        MutableLiveData<List<YeuThich>> data = new MutableLiveData<>();
        Common.apiService.getFavorites().enqueue(new Callback<List<YeuThich>>() {
            @Override
            public void onResponse(Call<List<YeuThich>> call, Response<List<YeuThich>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<YeuThich>> call, Throwable t) {

            }
        });
        return data;
    }


    public LiveData<List<TaiLieu>> getFavoriteBooks(int idDg) {
        final MutableLiveData<List<TaiLieu>> favoriteBooksData = new MutableLiveData<>();

        Common.apiService.getFavorites().enqueue(new Callback<List<YeuThich>>() {
            @Override
            public void onResponse(Call<List<YeuThich>> call, Response<List<YeuThich>> response) {
                if (response.isSuccessful()) {
                    List<YeuThich> favoriteItems = response.body();
                    List<TaiLieu> favoriteBooksList = new ArrayList<>();

                    for (YeuThich item : favoriteItems) {
                        // Lấy thông tin tài liệu dựa trên id_tailieu từ danh sách tài liệu
                        Common.apiService.getDetailOfBook(item.getId_tailieu()).enqueue(new Callback<TaiLieu>() {
                            @Override
                            public void onResponse(Call<TaiLieu> bookDetailCall, Response<TaiLieu> bookResponse) {
                                if (bookResponse.isSuccessful()) {
                                    TaiLieu book = bookResponse.body();
                                    favoriteBooksList.add(book);

                                    // Kiểm tra xem đã thêm hết tất cả tài liệu yêu thích vào danh sách chưa
                                    if (favoriteBooksList.size() == favoriteItems.size()) {
                                        favoriteBooksData.setValue(favoriteBooksList);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<TaiLieu> bookDetailCall, Throwable t) {
                                // Xử lý lỗi khi gọi API lấy thông tin tài liệu
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<YeuThich>> call, Throwable t) {
                // Xử lý lỗi khi gọi API yêu thích
            }
        });

        return favoriteBooksData;
    }
}