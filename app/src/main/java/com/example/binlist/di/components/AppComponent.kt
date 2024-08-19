package com.example.binlist.di.components

import android.content.Context
import com.example.binlist.di.modules.DbModule
import com.example.binlist.di.modules.DbRepositoryModule
import com.example.binlist.di.modules.NetworkModule
import com.example.binlist.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        DbModule::class,
        DbRepositoryModule::class
    ]
)
interface AppComponent {

    fun searchComponent(): SearchComponent

    fun historyComponent(): HistoryComponent

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}