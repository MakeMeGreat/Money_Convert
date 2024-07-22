package com.example.moneyconvert.di

import com.example.moneyconvert.data.RepositoryImpl
import com.example.moneyconvert.domain.Repository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}