package com.example.qlthuvien;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LiveDataTestUtil {
    public static <T> T getOrAwaitValue(LiveData<T> liveData, long time, TimeUnit timeUnit, Runnable afterObserve) throws InterruptedException, TimeoutException {
        T[] data = (T[]) new Object[1]; // Using an array to store the data so it can be modified in the Observer
        CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T value) {
                data[0] = value;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };

        liveData.observeForever(observer);
        afterObserve.run();

        if (!latch.await(time, timeUnit)) {
            liveData.removeObserver(observer);
            throw new TimeoutException("LiveData value never set");
        }

        //noinspection unchecked
        return data[0];
    }
}
