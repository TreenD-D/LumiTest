package com.lumitest.global.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yariksoffice.lingver.Lingver
import org.koin.android.ext.android.inject
import com.lumitest.R
import com.lumitest.global.dispatcher.error.ErrorHandler
import com.lumitest.global.ui.fragment.BaseFragment
import com.lumitest.data.preference.PreferencesWrapper
import com.lumitest.domain.model.enums.Language
import java.util.*

abstract class BaseActivity : FragmentActivity() {
    private val preferences: PreferencesWrapper by inject()
    private val errorHandler: ErrorHandler by inject()

    private val currentFragment: BaseFragment<*>?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        setLanguage()
        super.onCreate(savedInstanceState)
    }

    @Suppress("DEPRECATION")
    private fun setLanguage() {
        if (!preferences.language.isSet()) {
            setDefaultLanguage()
        }

        Lingver.getInstance().setLocale(application, preferences.language.get())
        errorHandler.resources = baseContext.resources
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        errorHandler.resources = baseContext.resources
    }

    private fun setDefaultLanguage() {
        preferences.language.set(
            Language[Locale.getDefault().language].tag
        )
    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: super.onBackPressed()
}
