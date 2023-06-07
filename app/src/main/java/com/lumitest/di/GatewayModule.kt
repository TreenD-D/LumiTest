package com.lumitest.di

import com.lumitest.data.gateway.AuthGatewayImpl
import com.lumitest.data.gateway.NotificationGatewayImpl
import com.lumitest.data.gateway.TransactionsGatewayImpl
import com.lumitest.domain.gateway.AuthGateway
import com.lumitest.domain.gateway.NotificationGateway
import com.lumitest.domain.gateway.TransactionsGateway
import org.koin.dsl.module

internal val gatewayModule = module {
    single<AuthGateway> { AuthGatewayImpl(get()) }

    single<NotificationGateway> { NotificationGatewayImpl(get(), get()) }

    single<TransactionsGateway> { TransactionsGatewayImpl(get()) }
}