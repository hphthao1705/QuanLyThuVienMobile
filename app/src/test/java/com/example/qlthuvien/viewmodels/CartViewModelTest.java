package com.example.qlthuvien.viewmodels;

import static org.junit.Assert.*;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
//import androidx.test.runner.AndroidJUnit4;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.qlthuvien.LiveDataTestUtil;
import com.example.qlthuvien.data.local.CartDatabase;
import com.example.qlthuvien.data.local.dao.CartDAO;
import com.example.qlthuvien.data.local.entities.Cart;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import kotlin.jvm.Throws;

@RunWith(AndroidJUnit4.class)
public class CartViewModelTest {
    public CartDatabase cartDatabase;
    public CartDAO cartDAO;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Before
    public void setUp(){
        Context context = ApplicationProvider.getApplicationContext();
        cartDatabase = Room.inMemoryDatabaseBuilder(context, CartDatabase.class)
                .allowMainThreadQueries()
                .build();
        cartDAO = cartDatabase.cartDao();
    }
    @Test
    @Throws(exceptionClasses = Exception.class)
    public void whenInsertNewBookToCart_thenDatabaseWillHaveThatBook() throws InterruptedException, TimeoutException {
        Cart cart = new Cart(1,1,"hinh1.jpg", "Sach 1", "Tac gia", 0);
        cartDAO.insertCart(cart);

        //List<Cart> result = cartDAO.getUserCart(1).getValue();
        List<Cart> result = LiveDataTestUtil.getOrAwaitValue(cartDAO.getUserCart(1), 5, TimeUnit.SECONDS, () -> {
            // Optional: Perform actions after observing LiveData, if needed
        });

        assertEquals(1, result.size());
        assertEquals("Sach 1", result.get(0).getTensach());
    }

    @Test
    @Throws(exceptionClasses = Exception.class)
    public void whenDeleteBook_thenDatabaseIsEmpty() throws InterruptedException, TimeoutException {
        cartDAO.deleteCart(1,1);

        List<Cart> result = LiveDataTestUtil.getOrAwaitValue(cartDAO.getUserCart(1), 5, TimeUnit.SECONDS, () -> {
            // Optional: Perform actions after observing LiveData, if needed
        });

        assertEquals(0, result.size());
    }

    @After
    public void tearDown(){
        cartDatabase.close();
    }
}

