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
    List<Cart> getUserCart(int id_dg);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCart(Cart cart);
    @Query("delete from cart where id_tailieu = :id_tailieu and id_dg = :id_dg")
    void deleteCart(int id_tailieu, int id_dg);
}
