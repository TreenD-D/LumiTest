package com.lumitest.di

import org.koin.dsl.module
import com.lumitest.feature.AppViewModel
import com.lumitest.feature.splash.SplashViewModel
import com.lumitest.global.viewmodel.NavigationViewModel
import com.lumitest.feature.verifyaddress.VerifyAddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf

internal val viewModelModule = module {
    viewModelOf(::NavigationViewModel)
    viewModelOf(::AppViewModel)

    viewModelOf(::SplashViewModel)
    viewModelOf(::VerifyAddressViewModel)
}