package com.lumitest.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TransactionsResponse(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("result") val result: List<TransactionDto>
)

@Serializable
class TransactionDto(
    @SerialName("blockNumber") val blockNumber: String,
    @SerialName("timeStamp") val timeStamp: String,
    @SerialName("hash") val hash: String,
    @SerialName("nonce") val nonce: String,
    @SerialName("blockHash") val blockHash: String,
    @SerialName("transactionIndex") val transactionIndex: String,
    @SerialName("from") val from: String,
    @SerialName("to") val to: String,
    @SerialName("value") val value: String,
    @SerialName("gas") val gas: String,
    @SerialName("gasPrice") val gasPrice: String,
    @SerialName("isError") val isError: String,
    @SerialName("txreceipt_status") val txReceiptStatus: String,
    @SerialName("input") val input: String,
    @SerialName("contractAddress") val contractAddress: String,
    @SerialName("cumulativeGasUsed") val cumulativeGasUsed: String,
    @SerialName("gasUsed") val gasUsed: String,
    @SerialName("confirmations") val confirmations: String,
    @SerialName("methodId") val methodId: String,
    @SerialName("functionName") val functionName: String
)