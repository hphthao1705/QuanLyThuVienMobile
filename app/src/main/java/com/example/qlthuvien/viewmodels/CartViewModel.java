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
import com.example.qlthuvien.respository.CartRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

public class CartViewModel extends AndroidViewModel {
    private CartRepository repository;
    public LiveData<List<Cart>> cartBook;
    public LiveData<Integer> count;
    public CartViewModel(Application application) {
        super(application);
        repository = new CartRepository(application);
        cartBook = repository.getAllBookFromUser();
        count = repository.countBookWhichIsChoosen();
    }
    public void insert(Cart cart)
    {
        repository.insert(cart);
    }
    public LiveData<Integer> countBookInCart(int id_tailieu)
    {
        return repository.countBookInCart(id_tailieu);
    }
    public void delete(Cart cart) { repository.delete(cart);}
    public void update(int checked, int id_tailieu)
    {
        repository.update(checked, id_tailieu);
    }
    public void deleteBooksWhichIsBorrowed()
    {
        repository.deleteBooksWhichIsBorrowed();
    }
}
