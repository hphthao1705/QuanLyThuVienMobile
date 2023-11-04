package com.example.qlthuvien.respository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.local.CartDatabase;
import com.example.qlthuvien.data.local.dao.CartDAO;
import com.example.qlthuvien.data.local.entities.Cart;

import java.util.List;

public class CartRepository {
    private CartDAO cartDAO;
    private LiveData<List<Cart>> allBooks;
    public LiveData<Integer> countBook;
    int id_dg = 0;
    public CartRepository(Application application)
    {
        CartDatabase database = CartDatabase.getInstance(application);
        cartDAO = database.cartDao();
        allBooks = cartDAO.getUserCart(id_dg);
        countBookWhichIsChoosen();
    }
    public LiveData<List<Cart>> getAllBookFromUser()
    {
        return allBooks;
    }
    public LiveData<Integer> countBookInCart(int id_tailieu)
    {
        return cartDAO.countBookInCart(id_dg, id_tailieu);
    }
    public void delete(Cart cart) { new DeleteBookAsyncTask(cartDAO).execute(cart); }
    public void insert(Cart cart)
    {
        new InsertBookAsyncTask(cartDAO).execute(cart);
    }
    public void update(int checked, int id_tailieu){
//        if(checked)
//        {
//            cartDAO.updateCheckBox(1, id_dg, id_tailieu);
//        }
//        cartDAO.updateCheckBox(0, id_dg, id_tailieu);
        cartDAO.updateCheckBox(checked, id_dg, id_tailieu);
    }
    public LiveData<Integer> countBookWhichIsChoosen()
    {
        return cartDAO.countBookWhichIsChoosen(id_dg);
    }
    public void deleteBooksWhichIsBorrowed()
    {
        cartDAO.deleteBooksWhichIsBorrowed(id_dg);
    }
    private static class InsertBookAsyncTask extends AsyncTask<Cart, Void, Void>{
        private CartDAO cartDAO;
        private InsertBookAsyncTask(CartDAO cartDAO)
        {
            this.cartDAO = cartDAO;
        }
        @Override
        protected Void doInBackground(Cart... carts) {
            cartDAO.insertCart(carts[0]);
            return null;
        }
    }
    private static class DeleteBookAsyncTask extends AsyncTask<Cart, Void, Void>{
        private CartDAO cartDAO;
        private DeleteBookAsyncTask(CartDAO cartDAO)
        {
            this.cartDAO = cartDAO;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            cartDAO.deleteCart(carts[0].getId_tailieu(),carts[0].getId_dg());
            return null;
        }
    }
}
