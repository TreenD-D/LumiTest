package com.lumitest.domain.gateway

import com.lumitest.domain.model.transactions.TransactionData

interface TransactionsGateway {
    suspend fun getTransactions(walletAddress: String): TransactionData
}