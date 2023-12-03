package com.example.qlthuvien.viewmodels;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.YeuThich;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(JUnit4.class)
public class YeuThichViewModelTest{
    @Mock
    private YeuThichViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void whenLiveDataHaveData_thenTestIsPass(){
        ArrayList<YeuThich> list = new ArrayList<>();

        YeuThich yt1 = new YeuThich(1,1,1);
        YeuThich yt2 = new YeuThich(1,2,2);

        list.add(yt1);
        list.add(yt2);

        MutableLiveData<List<YeuThich>> liveData = new MutableLiveData<>();
        liveData.postValue(list);

        Mockito.when(viewModel.getFavourites()).thenReturn(liveData);

        LiveData<List<YeuThich>> getYeuThich = viewModel.getFavourites();

        assertNotNull(getYeuThich);

        //Mockito.verify(viewModel).liveData_YT.getValue();
    }
    @Test
    public void whenLiveDataDontHaveData_thenTestIsPass()
    {
        ArrayList<YeuThich> list = new ArrayList<>();

        MutableLiveData<List<YeuThich>> liveData = new MutableLiveData<>();
        liveData.postValue(list);

        Mockito.when(viewModel.getFavourites()).thenReturn(liveData);

        LiveData<List<YeuThich>> getYeuThich = viewModel.getFavourites();

        assertEquals(0, getYeuThich.getValue().size());

        //Mockito.verify(viewModel).liveData_YT.getValue();
    }
}