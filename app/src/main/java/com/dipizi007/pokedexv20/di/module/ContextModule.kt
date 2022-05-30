package com.dipizi007.pokedexv20.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun getContext(): Context {
        return context.applicationContext
    }
}