package com.lumitest.feature.splash

import android.os.Bundle
import android.view.View
import com.lumitest.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lumitest.databinding.FragmentSplashBinding
import com.lumitest.global.ui.fragment.BaseFragment
import com.lumitest.global.utils.BindingProvider

private const val ANIMATION_DURATION_MS = 3000L

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val viewModel: SplashViewModel by viewModel()

    override var isLightStatusBar = true

    override val bindingProvider: BindingProvider<FragmentSplashBinding> =
        FragmentSplashBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startFirstAnimation()
        // activate this if splash background color differs from main window background color
        // view.postDelayed(
        //     { activity?.window?.setBackgroundDrawableResource(R.color.colorBackground) },
        //     ANIMATION_DURATION_MS / 2
        // )

         navigation.newRootFlow(Screens.Flow.addressInput())
    }

    private fun startFirstAnimation() {
        viewScope.launch {
            delay(ANIMATION_DURATION_MS)
            viewModel.onAnimationEnd()
        }
    }
}