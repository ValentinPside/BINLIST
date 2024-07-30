package com.example.binlist.di

import android.content.Context
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