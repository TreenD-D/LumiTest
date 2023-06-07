package com.lumitest.data.gateway

import com.lumitest.data.global.ModelMapper
import com.lumitest.data.network.CommonApi
import com.lumitest.domain.gateway.TransactionsGateway

class TransactionsGatewayImpl(
    private val commonApi: CommonApi
) : TransactionsGateway {
    override suspend fun getTransactions(walletAddress: String) =
        commonApi.getAccountTransactions(address = walletAddress).let(ModelMapper.transactionsModelMapper)

}