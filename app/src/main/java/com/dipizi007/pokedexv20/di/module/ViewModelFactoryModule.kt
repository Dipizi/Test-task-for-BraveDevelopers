package com.dipizi007.pokedexv20.di.module

import androidx.lifecycle.ViewModelProvider
import com.dipizi007.pokedexv20.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(vm: ViewModelFactory): ViewModelProvider.Factory

}