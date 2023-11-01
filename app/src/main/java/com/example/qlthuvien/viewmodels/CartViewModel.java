package com.example.qlthuvien.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.local.CartDatabase;
import com.example.qlthuvien.data.local.entities.Cart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    public MutableLiveData<List<Cart>> cartBook;
    private CartDatabase cartDatabase;
    public CartViewModel(Application application) {
        super(application);
        cartBook = new MutableLiveData<>();
        cartDatabase = CartDatabase.getInstance(getApplication().getApplicationContext());
    }
    public MutableLiveData<List<Cart>> getCartBook()
    {
        return cartBook;
    }
    public void getUserCart(int id_dg)
    {
        List<Cart> list = cartDatabase.cartDao().getUserCart(id_dg);
        if(list.size() > 0)
        {
            cartBook.postValue(list);
        }
        else
        {
            cartBook.postValue(null);
        }
    }
    public void insert(Cart cart)
    {
        cartDatabase.cartDao().insertCart(cart);
    }
}
