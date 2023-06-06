package com.lumitest.domain.interactor.notification

import com.lumitest.domain.gateway.NotificationGateway
import com.lumitest.domain.global.DispatcherProvider
import com.lumitest.domain.global.UseCaseWithParams

class RegisterFirebaseTokenUseCase(
    private val notificationGateway: NotificationGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<String, Unit>(dispatcherProvider.io) {
    override suspend fun execute(parameters: String) =
        notificationGateway.registerNotificationToken(parameters)
}