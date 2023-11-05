package com.example.qlthuvien.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.qlthuvien.data.local.entities.Cart;
import java.util.List;
import androidx.room.Query;

@Dao
public interface CartDAO {
    @Query("select * from cart where id_dg = :id_dg")
    LiveData<List<Cart>> getUserCart(int id_dg);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCart(Cart cart);
    @Query("delete from cart where id_tailieu = :id_tailieu and id_dg = :id_dg")
    void deleteCart(int id_tailieu, int id_dg);
    @Query("select count(*) from cart where id_dg = :id_dg and id_tailieu = :id_tailieu")
    LiveData<Integer> countBookInCart(int id_dg, int id_tailieu);
    @Query("update cart set checkbox = :checked where id_dg = :id_dg and id_tailieu = :id_tailieu")
    void updateCheckBox(int checked, int id_dg, int id_tailieu);
    @Query("select count(*) from cart where id_dg = :id_dg and checkbox = 1")
    LiveData<Integer> countBookWhichIsChoosen(int id_dg);
    @Query("delete from cart where id_dg = :id_dg and checkbox = 1")
    void deleteBooksWhichIsBorrowed(int id_dg);
}
