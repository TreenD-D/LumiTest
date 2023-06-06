package com.lumitest.domain.gateway

interface AuthGateway {
    suspend fun getAuthorizationToken(): String
}