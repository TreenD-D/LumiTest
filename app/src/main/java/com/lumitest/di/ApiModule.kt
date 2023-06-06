package com.lumitest.di

import com.lumitest.data.network.AuthApi
import com.lumitest.data.network.CommonApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal val apiModule = module {
    single { get<Retrofit>(named("AuthRetrofit")).create(AuthApi::class.java) }
    single { get<Retrofit>(named("CommonRetrofit")).create(CommonApi::class.java) }
}