package com.example.qlthuvien.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.YeuThich;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("docgia")
    Call<List<DocGia>> getUser();

    @GET("tailieu")
    Call<List<TaiLieu>> getBook();

    @GET("chitietmuontra")
    Call<List<ChiTietMuonTra>> getDetailOfBorrowBooks();

    @GET("loai")
    Call<List<Loai>> getCategories();
    @GET("yeuthich")
    Call<List<YeuThich>> getFavorites();
    @GET("tailieu/{id}")
    Call<TaiLieu> getDetailOfBook(@Path("id") int id_tailieu);

}
