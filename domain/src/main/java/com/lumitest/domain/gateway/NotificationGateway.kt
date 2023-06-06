package com.lumitest.domain.gateway

interface NotificationGateway {
    suspend fun registerNotificationToken(newToken: String)
}