package com.example.qlthuvien.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.qlthuvien.data.local.dao.CartDAO;
import com.example.qlthuvien.data.local.entities.Cart;

@Database(entities = Cart.class, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase instance;
    public abstract CartDAO cartDao();
    public static synchronized CartDatabase getInstance(Context context)
    {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CartDatabase.class, "cart").build();
        }
        return instance;
    }
}
