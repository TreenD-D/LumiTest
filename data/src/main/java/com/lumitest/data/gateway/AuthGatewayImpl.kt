package com.lumitest.data.gateway

import com.lumitest.data.network.CommonApi
import com.lumitest.domain.gateway.AuthGateway

class AuthGatewayImpl(
    private val commonApi: CommonApi
) : AuthGateway {
    override suspend fun getAuthorizationToken(): String {
        commonApi.getSampleData(0.0).lat.toString()
        TODO("STUB")
    }
}