package com.example.qlthuvien.respository;

import static android.app.PendingIntent.getActivity;
import static android.content.Context.MODE_PRIVATE;
import static com.example.qlthuvien.view.activities.LoginActivity.GIOITINH;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.local.CartDatabase;
import com.example.qlthuvien.data.local.dao.CartDAO;
import com.example.qlthuvien.data.local.entities.Cart;
import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.view.activities.MainActivity;

import java.util.List;

public class CartRepository{
    private CartDAO cartDAO;
    private LiveData<List<Cart>> allBooks;
    public CartRepository(Application application)
    {
        CartDatabase database = CartDatabase.getInstance(application);
        cartDAO = database.cartDao();
    }
    public void getUserCart(int id_dg)
    {
        allBooks = cartDAO.getUserCart(id_dg);
    }
    public LiveData<List<Cart>> getAllBookFromUser()
    {
        return allBooks;
    }
    public LiveData<Integer> countBookInCart(int id_tailieu, int id_dg)
    {
        return cartDAO.countBookInCart(id_dg, id_tailieu);
    }
    public void delete(Cart cart) { new DeleteBookAsyncTask(cartDAO).execute(cart); }
    public void insert(Cart cart)
    {
        new InsertBookAsyncTask(cartDAO).execute(cart);
    }
    public void update(int checked, int id_tailieu, int id_dg){
        cartDAO.updateCheckBox(checked, id_dg, id_tailieu);
    }
    public LiveData<Integer> countBookWhichIsChoosen(int id_dg)
    {
        return cartDAO.countBookWhichIsChoosen(id_dg);
    }
    public LiveData<List<Cart>> getBooksAddToCallCart(int id_dg)
    {
        return cartDAO.addBooksToCallCard(id_dg);
    }
    public void deleteBooksWhichIsBorrowed(int id_dg)
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
