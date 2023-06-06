package com.lumitest.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.lumitest.feature.AppViewModel
import com.lumitest.feature.splash.SplashViewModel
import com.lumitest.global.viewmodel.NavigationViewModel

internal val viewModelModule = module {
    viewModel { NavigationViewModel(get()) }
    viewModel { AppViewModel() }

    viewModel { SplashViewModel(get(), get(), get()) }
}