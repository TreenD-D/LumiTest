package com.lumitest.domain.interactor.transactions

import com.lumitest.domain.gateway.TransactionsGateway
import com.lumitest.domain.global.DispatcherProvider
import com.lumitest.domain.global.UseCaseWithParams
import com.lumitest.domain.model.transactions.TransactionData

class GetTransactionsUseCase(
    private val transactionsGateway: TransactionsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<String, TransactionData>(dispatcherProvider.io) {
    override suspend fun execute(parameters: String): TransactionData =
        transactionsGateway.getTransactions(parameters)
}