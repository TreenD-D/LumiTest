package com.lumitest.feature.verifyaddress

import androidx.lifecycle.ViewModel
import com.lumitest.global.dispatcher.error.ErrorHandler
import com.lumitest.global.dispatcher.notifier.Notifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256
import org.bouncycastle.util.encoders.Hex

class VerifyAddressViewModel(
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<VerifyAddressScreenState>(VerifyAddressScreenState())
    val screenState = _screenState.asStateFlow()

    fun updateInputText(text: String) {
        _screenState.tryEmit(
            VerifyAddressScreenState(
                isValidAddress = isValidEthAddress(text),
                addressText = text
            )
        )
    }


    private fun isValidEthAddress(address: String): Boolean {
        val cleanAddress = address.removePrefix("0x") // Remove "0x" prefix
        val hexRegex = Regex("[0-9A-Fa-f]{40}") // Regex pattern to match 40 hexadecimal characters

        if (address.length != 42 || !address.startsWith("0x") || !hexRegex.matches(cleanAddress)) {
            return false
        }

        // Perform checksum validation
        val addressNoChecksum = cleanAddress.toLowerCase()
        val addressBytes = Hex.decode(addressNoChecksum)
        val sha3Digest = Digest256()
        val addressHash = sha3Digest.digest(addressBytes)
        val addressHashHex = Hex.toHexString(addressHash)

        for (i in 0 until 40) {
            val shouldBeUpperCase = addressNoChecksum[i].isUpperCase()
            val isUpperCase = addressHashHex[i].isUpperCase()
            if (shouldBeUpperCase != isUpperCase) {
                return false
            }
        }

        return true
    }

}


data class VerifyAddressScreenState(
    val isValidAddress: Boolean = false,
    val addressText: String? = null
)