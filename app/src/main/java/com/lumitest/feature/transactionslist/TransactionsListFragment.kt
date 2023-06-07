package com.lumitest.feature.transactionslist

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.lumitest.databinding.FragmentTransactionsListBinding
import com.lumitest.global.ui.fragment.BaseFragment
import com.lumitest.global.utils.BindingProvider
import com.lumitest.global.utils.argument
import com.lumitest.global.utils.fastAdapter
import com.lumitest.global.utils.itemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.launch
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import pro.appcraft.lib.utils.common.setVisible

class TransactionsListFragment : BaseFragment<FragmentTransactionsListBinding>() {
    override val bindingProvider: BindingProvider<FragmentTransactionsListBinding> =
        FragmentTransactionsListBinding::inflate
    private val viewModel: TransactionsListViewModel by viewModel()

    private val walletAddress: String? by argument(KEY_WALLED_ADDRESS)

    private val itemAdapter: ItemAdapter<TransactionItem> by itemAdapter()
    private val fastAdapter: FastAdapter<TransactionItem> by fastAdapter { listOf(itemAdapter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)
        walletAddress?.let { viewModel.setWallet(it) }
        bindRecycler()
        initObservers()
        initListeners()
    }

    private fun bindRecycler() {
        binding.transactionsListView.adapter = fastAdapter

        fastAdapter.onClickListener =
            { v: View?, _: IAdapter<TransactionItem>, item: TransactionItem, _: Int ->
                if (v != null) {
                    val decodedInput = viewModel.decodeERC20Input(item.entry.input)
                    showTransactionInputDialog(requireContext(), decodedInput)
                }
                false
            }
    }

    private fun initObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->

                    //currently list from api can be empty, there are no checks here(showing empty recycler)
                    state.list?.map(::TransactionItem)
                        .also { list ->
                            if (list != null)
                                FastAdapterDiffUtil[itemAdapter] =
                                    FastAdapterDiffUtil.calculateDiff(itemAdapter, list)
                        }

                    binding.apply {
                        errorGroup.setVisible(
                            state.isError
                        )

                        progressView.progressContent.setVisible(
                            state.isLoading
                        )

                        transactionsListView.setVisible(
                            !state.isError && !state.isLoading
                        )
                    }


                }
            }
        }
    }

    private fun initListeners() {
        binding.refreshTransactionsButton.setOnClickListener {
            viewModel.reload()
        }
    }

    private fun showTransactionInputDialog(context: Context, input: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Transaction Input")

        val inputEditText = EditText(context)
        inputEditText.setText(input)
        inputEditText.isEnabled = false

        builder.setView(inputEditText)

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }


    companion object {
        const val KEY_WALLED_ADDRESS = "wallet_address"
    }

}