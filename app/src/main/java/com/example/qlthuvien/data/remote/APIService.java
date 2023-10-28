package com.example.qlthuvien.data.remote;

import com.example.qlthuvien.data.model.DocGia;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface APIService {
    @GET("docgia")
    Flowable<DocGia> getUser();
}
