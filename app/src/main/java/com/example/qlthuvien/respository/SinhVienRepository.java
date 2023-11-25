package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.SinhVien;
import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SinhVienRepository {

    public LiveData<List<SinhVien>> getAllSinhVienInfo() {
        MutableLiveData<List<SinhVien>> sinhVienListLiveData = new MutableLiveData<>();
        Call<List<SinhVien>> call = Common.apiService.getSV();
        call.enqueue(new Callback<List<SinhVien>>() {
            @Override
            public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
                if (response.isSuccessful()) {
                    sinhVienListLiveData.setValue(response.body());
                } else {
                    sinhVienListLiveData.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<SinhVien>> call, Throwable t) {
                sinhVienListLiveData.setValue(new ArrayList<>());
            }
        });
        return sinhVienListLiveData;
    }



//    public LiveData<List<SinhVien>> getAllSinhVienInfo() {
//        MutableLiveData<List<SinhVien>> sinhVienListLiveData = new MutableLiveData<>();
//        Call<List<SinhVien>> call = Common.apiService.getSV();
//        call.enqueue(new Callback<List<SinhVien>>() {
//            @Override
//            public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
//                if (response.isSuccessful()) {
//                    sinhVienListLiveData.setValue(response.body());
//                } else {
//                    sinhVienListLiveData.setValue(new ArrayList<>());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<SinhVien>> call, Throwable t) {
//                sinhVienListLiveData.setValue(new ArrayList<>());
//            }
//        });
//        return sinhVienListLiveData;
//    }

    public LiveData<DocGia> registerDocGia(DocGia docGia) {
        MutableLiveData<DocGia> docGiaLiveData = new MutableLiveData<>();
        Call<DocGia> call = Common.apiService.getUserRegiDG(docGia);
        call.enqueue(new Callback<DocGia>() {
            @Override
            public void onResponse(Call<DocGia> call, Response<DocGia> response) {
                if (response.isSuccessful()) {
                    docGiaLiveData.setValue(response.body());
                } else {
                    docGiaLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DocGia> call, Throwable t) {
                docGiaLiveData.setValue(null);
            }
        });
        return docGiaLiveData;
    }

    public LiveData<Boolean> checkEmailExistence(String email) {
        MutableLiveData<Boolean> accountExistenceLiveData = new MutableLiveData<>();
        Call<List<DocGia>> call = Common.apiService.getUser();
        call.enqueue(new Callback<List<DocGia>>() {
            @Override
            public void onResponse(Call<List<DocGia>> call, Response<List<DocGia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (DocGia docGia : response.body()) {
                        if (docGia.getEmail().equals(email)) {
                            accountExistenceLiveData.setValue(true);
                            return;
                        }
                    }
                }
                accountExistenceLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<List<DocGia>> call, Throwable t) {
                accountExistenceLiveData.setValue(false);
            }
        });
        return accountExistenceLiveData;
    }

//    public LiveData<Boolean> checkAccountExistence(String mssv) {
//        MutableLiveData<Boolean> accountExistenceLiveData = new MutableLiveData<>();
//        Call<List<DocGia>> call = Common.apiService.getUser();
//        call.enqueue(new Callback<List<DocGia>>() {
//            @Override
//            public void onResponse(Call<List<DocGia>> call, Response<List<DocGia>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    for (DocGia docGia : response.body()) {
//                        if (docGia.getEmail().equals(mssv)) {
//                            accountExistenceLiveData.setValue(true);
//                            return;
//                        }
//                    }
//                }
//                accountExistenceLiveData.setValue(false);
//            }
//
//            @Override
//            public void onFailure(Call<List<DocGia>> call, Throwable t) {
//                accountExistenceLiveData.setValue(false);
//            }
//        });
//        return accountExistenceLiveData;
//    }

    public LiveData<Boolean> checkAccountExistence(int id_sv) {
        MutableLiveData<Boolean> accountExistenceLiveData = new MutableLiveData<>();
        Call<List<DocGia>> call = Common.apiService.getUser();
        call.enqueue(new Callback<List<DocGia>>() {
            @Override
            public void onResponse(Call<List<DocGia>> call, Response<List<DocGia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (DocGia docGia : response.body()) {
                        if (docGia.getId_sv() == id_sv) {
                            accountExistenceLiveData.setValue(true);
                            return;
                        }
                    }
                }
                accountExistenceLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<List<DocGia>> call, Throwable t) {
                accountExistenceLiveData.setValue(false);
            }
        });
        return accountExistenceLiveData;
    }


    public LiveData<Boolean> checkMssvExistence(String mssv) {
        MutableLiveData<Boolean> mssvExistenceLiveData = new MutableLiveData<>();
        Call<List<SinhVien>> call = Common.apiService.getSV();
        call.enqueue(new Callback<List<SinhVien>>() {
            @Override
            public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (SinhVien sinhVien : response.body()) {
                        if (sinhVien.getMssv().equals(mssv)) {
                            mssvExistenceLiveData.setValue(true);
                            return;
                        }
                    }
                }
                mssvExistenceLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<List<SinhVien>> call, Throwable t) {
                mssvExistenceLiveData.setValue(false);
            }
        });
        return mssvExistenceLiveData;
    }
}
