package com.example.qlthuvien.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.qlthuvien.data.local.dao.CartDAO;
import com.example.qlthuvien.data.local.entities.Cart;

@Database(entities = {Cart.class}, version = 3)
public abstract class CartDatabase extends RoomDatabase {
    static CartDatabase instance;

    public abstract CartDAO cartDao();

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, "cart")
                    .allowMainThreadQueries()
                    .build();
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                            CartDatabase.class, "cart")
//                    .fallbackToDestructiveMigration()
//                    .build();

        }
        return instance;
    }
}
