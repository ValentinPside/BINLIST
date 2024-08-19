package com.example.binlist.di.modules

import com.example.binlist.data.DbRepositoryImpl
import com.example.binlist.domain.DbRepository
import dagger.Module
import dagger.Provides

@Module
object DbRepositoryModule {

    @Provides
    fun provideDbRepository(impl: DbRepositoryImpl): DbRepository = impl

}