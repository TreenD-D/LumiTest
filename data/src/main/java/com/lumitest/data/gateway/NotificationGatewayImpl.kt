package com.lumitest.data.gateway

import com.lumitest.data.network.AuthApi
import com.lumitest.data.network.model.FirebaseTokenPairModel
import com.lumitest.data.preference.PreferencesWrapper
import com.lumitest.domain.gateway.NotificationGateway

class NotificationGatewayImpl(
    private val authApi: AuthApi,
    private val preferences: PreferencesWrapper
) : NotificationGateway {
    override suspend fun registerNotificationToken(newToken: String) {
        val tokenPair = FirebaseTokenPairModel(
            oldToken = preferences.firebaseToken.get(),
            newToken = newToken
        )
        preferences.firebaseToken.set(newToken)

        TODO("STUB")
    }
}