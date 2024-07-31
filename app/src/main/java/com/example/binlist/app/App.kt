package com.example.binlist.app

import android.app.Application
import com.example.binlist.di.components.AppComponent
import com.example.binlist.di.components.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}