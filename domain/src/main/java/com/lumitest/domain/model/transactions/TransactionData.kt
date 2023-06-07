package com.lumitest.domain.model.transactions

data class TransactionData(
    val result: List<Transaction>
)

class Transaction(
    val blockNumber: String,
    val timeStamp: String,
    val hash: String,
    val nonce: String,
    val blockHash: String,
    val transactionIndex: String,
    val from: String,
    val to: String,
    val value: String,
    val gas: String,
    val gasPrice: String,
    val isError: String,
    val txReceiptStatus: String,
    val input: String,
    val contractAddress: String,
    val cumulativeGasUsed: String,
    val gasUsed: String,
    val confirmations: String,
    val methodId: String,
    val functionName: String
)