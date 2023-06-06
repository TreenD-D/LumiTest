package com.lumitest.di

import com.lumitest.domain.interactor.auth.GetAuthorizationTokenUseCase
import com.lumitest.domain.interactor.notification.RegisterFirebaseTokenUseCase
import org.koin.dsl.module

internal val interactorModule = module {
    single { GetAuthorizationTokenUseCase(get(), get()) }

    single { RegisterFirebaseTokenUseCase(get(), get()) }
}