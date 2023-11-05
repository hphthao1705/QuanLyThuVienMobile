package com.example.qlthuvien.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.local.CartDatabase;
import com.example.qlthuvien.data.local.entities.Cart;
import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.respository.CartRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

public class CartViewModel extends AndroidViewModel {
    private CartRepository repository;
    public LiveData<List<Cart>> cartBook;
    public LiveData<Integer> count;
    public LiveData<List<Cart>> listLiveData;
    public CartViewModel(Application application) {
        super(application);
        repository = new CartRepository(application);
    }
    public void getAllBookFromUser(int id_dg)
    {
        repository.getUserCart(id_dg);
        cartBook = repository.getAllBookFromUser();
    }
    public void countBookWhichIsChoosen(int id_dg)
    {
        count = repository.countBookWhichIsChoosen(id_dg);
    }
    public void insert(Cart cart)
    {
        repository.insert(cart);
    }
    public LiveData<Integer> countBookInCart(int id_tailieu, int id_dg)
    {
        return repository.countBookInCart(id_tailieu, id_dg);
    }
    public void delete(Cart cart) { repository.delete(cart);}
    public void update(int checked, int id_tailieu, int id_dg)
    {
        repository.update(checked, id_tailieu, id_dg);
    }
    public void deleteBooksWhichIsBorrowed(int id_dg)
    {
        repository.deleteBooksWhichIsBorrowed(id_dg);
    }
    public void addBooksToCallCard(int id_dg)
    {
        listLiveData = repository.getBooksAddToCallCart(id_dg);
    }
}
