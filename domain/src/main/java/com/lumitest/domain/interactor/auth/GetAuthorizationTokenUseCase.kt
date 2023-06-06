package com.lumitest.domain.interactor.auth

import com.lumitest.domain.gateway.AuthGateway
import com.lumitest.domain.global.DispatcherProvider
import com.lumitest.domain.global.UseCase

class GetAuthorizationTokenUseCase(
    private val authGateway: AuthGateway,
    dispatcherProvider: DispatcherProvider
) : UseCase<String>(dispatcherProvider.io) {
    override suspend fun execute() = authGateway.getAuthorizationToken()
}