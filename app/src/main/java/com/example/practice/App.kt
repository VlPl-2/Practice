package com.example.practice

import android.app.Application
import com.example.practice.di.appModule
import com.example.practice.di.dataModule
import com.example.practice.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }

    }
}
