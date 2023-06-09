package com.lumitest

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.lumitest.di.appComponent
import com.lumitest.observer.AppLifecycleObserver
import com.lumitest.data.preference.PreferencesWrapper
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.yariksoffice.lingver.Lingver

class App : Application() {
    private val appLifecycleObserver: AppLifecycleObserver by inject()
    private val preferences: PreferencesWrapper by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLifecycleObserver()
        initLanguage()
    }


    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(appComponent)
            fragmentFactory()
        }
    }

    private fun initLifecycleObserver() {
        ProcessLifecycleOwner
            .get()
            .lifecycle
            .addObserver(appLifecycleObserver)
    }

    private fun initLanguage() {
        Lingver.init(this, preferences.language.get())
    }
}