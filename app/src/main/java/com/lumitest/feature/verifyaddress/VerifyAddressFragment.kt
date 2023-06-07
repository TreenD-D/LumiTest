package com.lumitest.feature.verifyaddress

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.lumitest.R
import com.lumitest.Screens
import com.lumitest.databinding.FragmentVerifyaddressBinding
import com.lumitest.global.ui.fragment.BaseFragment
import com.lumitest.global.utils.BindingProvider
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerifyAddressFragment : BaseFragment<FragmentVerifyaddressBinding>() {
    private val viewModel: VerifyAddressViewModel by viewModel()

    override val bindingProvider: BindingProvider<FragmentVerifyaddressBinding> =
        FragmentVerifyaddressBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpViews()
        setUpStateObserver()

    }

    private fun setUpViews() {
        binding.apply {
            addressEditText.addTextChangedListener {
                viewModel.updateInputText(it.toString())
            }

            loadTransactionsButton.setOnClickListener {
                navigation.navigateTo(Screens.Screen.transactionsList(addressEditText.text.toString()))
            }


        }
    }

    private fun setUpStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: VerifyAddressScreenState) {
        binding.apply {
            if (!state.isValidAddress && !state.addressText.isNullOrBlank()) addressInput.error =
                getString(
                    R.string.address_not_valid
                ) else addressInput.error = null
            loadTransactionsButton.isEnabled = state.isValidAddress

        }
    }

}