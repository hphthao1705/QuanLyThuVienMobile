package com.example.qlthuvien.di;

import com.example.qlthuvien.viewmodels.YeuThichViewModel;

import dagger.Component;

@Component
public interface YeuThichComponent {
    YeuThichViewModel getViewModel();

}
