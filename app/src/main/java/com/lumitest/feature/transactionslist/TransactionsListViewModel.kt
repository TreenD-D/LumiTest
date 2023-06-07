package com.lumitest.feature.transactionslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumitest.domain.interactor.transactions.GetTransactionsUseCase
import com.lumitest.domain.model.transactions.Transaction
import com.lumitest.feature.transactionslist.model.TypedTransaction
import com.lumitest.global.dispatcher.error.ErrorHandler
import com.lumitest.global.dispatcher.notifier.Notifier
import com.lumitest.global.viewmodel.LoaderViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsListViewModel(
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    private var walletAddress: String = ""

    private val _screenState =
        MutableStateFlow<TransactionsListScreenState>(TransactionsListScreenState())
    val screenState = _screenState.asStateFlow()

    fun setWallet(wallet: String) {
        if (walletAddress != wallet) {
            walletAddress = wallet
            reload()
        }
    }

    fun switchLoading(isLoading: Boolean){
        _screenState.tryEmit(
            _screenState.value.copy(
                isLoading = isLoading
            )
        )
    }


    fun reload() {
        switchLoading(true)
        viewModelScope.launch {
            getTransactionsUseCase.invoke(walletAddress)
                .fold({
                    _screenState.emit(
                        TransactionsListScreenState(
                            list = it.result.map {entity ->
                                TypedTransaction(
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
                                    entity.functionName,
                                    entity.to == walletAddress.toLowerCase()
                                )
                            })
                    )
                },{
                    //in case we need to show error text from API's error json - need to wrap api call to Response<> and map error resp result(out of task's scope)
                    _screenState.emit(
                        TransactionsListScreenState(
                            isError = true
                        )
                    )
                })
        }
    }


    //should use res strings here, instead of current realization
    fun decodeERC20Input(input: String): String {
        return when (input.substring(0, 10)) {
            "0xa9059cbb" -> decodeERC20TransferInput(input)
            "0x095ea7b3" -> decodeERC20ApproveInput(input)
            else -> "Empty input(0x)"
        }
    }

    private fun decodeERC20TransferInput(input: String): String {
        val functionSignature = input.substring(0, 10)
        require(functionSignature == "0xa9059cbb") { "Invalid input for ERC20 transfer" }

        val address = input.substring(10, 74)
        val amount = input.substring(74)

        return "Transfer Address: $address, Transfer Amount: $amount"
    }

    private fun decodeERC20ApproveInput(input: String): String {
        val functionSignature = input.substring(0, 10)
        require(functionSignature == "0x095ea7b3") { "Invalid input for ERC20 approve" }

        val spenderAddress = input.substring(10, 74)
        val amount = input.substring(74)

        return "Spender Address: $spenderAddress, Approved Amount: $amount"
    }


}

data class TransactionsListScreenState(
    val list: List<TypedTransaction>? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)