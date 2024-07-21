package com.example.moneyconvert.di

import com.example.moneyconvert.presentation.ui.InputFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class]
)
interface AppComponent {
    fun inject(inputFragment: InputFragment)
}