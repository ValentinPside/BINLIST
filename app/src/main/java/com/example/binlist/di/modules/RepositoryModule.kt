package com.example.binlist.di.modules

import com.example.binlist.data.RepositoryImpl
import com.example.binlist.domain.Repository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(impl: RepositoryImpl): Repository = impl

}