package com.lumitest.feature.verifyaddress

import com.lumitest.Screens
import com.lumitest.global.ui.fragment.FlowFragment

class VerifyAddressFlowFragment : FlowFragment() {
    override val launchScreen = Screens.Screen.addressInput()
}