package com.lumitest.data.global

import com.lumitest.data.network.model.TransactionDto
import com.lumitest.data.network.model.TransactionsResponse
import com.lumitest.domain.model.transactions.Transaction
import com.lumitest.domain.model.transactions.TransactionData

object ModelMapper {
    val transactionsModelMapper: Mapper<TransactionsResponse, TransactionData> = { entity ->
        TransactionData(
            result = entity.result.map(singleTransactionMapper)
        )
    }

    private val singleTransactionMapper: Mapper<TransactionDto, Transaction> = { entity ->
        Transaction(
            entity.blockNumber,
            entity.timeStamp,
            entity.hash,
            entity.nonce,
            entity.blockHash,
            entity.transactionIndex,
            entity.from,
            entity.to,
            entity.value,
            entity.gas,
            entity.gasPrice,
            entity.isError,
            entity.txReceiptStatus,
            entity.input,
            entity.contractAddress,
            entity.cumulativeGasUsed,
            entity.gasUsed,
            entity.confirmations,
            entity.methodId,
            entity.functionName
        )
    }
}