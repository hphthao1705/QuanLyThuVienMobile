package com.example.qlthuvien.data.remote;

import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.data.model.NhaXuatBan;
import com.example.qlthuvien.data.model.SinhVien;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.YeuThich;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("docgia")
    Call<List<DocGia>> getUser();

    @GET("tailieu")
    Call<List<TaiLieu>> getBook();
    @GET("nhaxuatban/{id}")
    Call<NhaXuatBan> getPublisher(@Path("id") int id_nxb);

    @GET("chitietmuontra")
    Call<List<ChiTietMuonTra>> getDetailOfBorrowBooks();
    @GET("muontra")
    Call<List<MuonTra>> getListOfBorrowBook();
    @POST("muontra")
    Call<MuonTra> insertCallCard(@Body JsonObject body);
    @POST("chitietmuontra")
    Call<ChiTietMuonTra> insertDetailOfCallCard(@Body JsonObject body);

    @GET("loai")
    Call<List<Loai>> getCategories();
    @GET("tailieu/{id}")
    Call<TaiLieu> getDetailOfBook(@Path("id") int id_tailieu);

    @GET("yeuthich")
    Call<List<YeuThich>> getFavorites();

    // login
    @FormUrlEncoded
    @POST("docgia/add/{username}/{password}")
    Call<DocGia> getUserLogin(
            @Field("username") String email,
            @Field("password") String password
    );


    @GET("sv/{id}")
    Call<SinhVien> getSinhVien(
            @Path("id") int id_sv
    );

    // register
    @FormUrlEncoded
    @POST("sv")
    Call<SinhVien> getUserRegi(
            @Field("tensv") String tensv,
            @Field("gioitinh") int gioitinh
    );
    @FormUrlEncoded
    @POST("docgia")
    Call<DocGia> getUserRegis(
            @Field("id_dg") int id_dg,
            @Field("id_sv") int id_sv,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("docgia")
    Call<DocGia> getUserRegiDG(
            @Body DocGia dg
    );

    // update độc giả
    @PUT("docgia/{id}")
    Call<DocGia> updateUser(
            @Path("id") int id_dg,
            @Body DocGia docgia
    );


    // update SV
    @PUT("sv/{id}")
    Call<SinhVien> updateUserSV(
            @Path("id") int id_sv,
            @Body SinhVien sinhvien
    );
}
