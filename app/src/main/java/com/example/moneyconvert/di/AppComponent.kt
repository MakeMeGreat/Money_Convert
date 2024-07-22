package com.example.moneyconvert.di

import com.example.moneyconvert.presentation.ui.InputFragment
import com.example.moneyconvert.presentation.ui.OutputFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class, RepositoryModule::class]
)
interface AppComponent {
    fun inject(inputFragment: InputFragment)
    fun inject(outputFragment: OutputFragment)
}