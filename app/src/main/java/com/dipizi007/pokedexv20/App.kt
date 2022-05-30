package com.dipizi007.pokedexv20


import android.app.Application
import com.dipizi007.pokedexv20.di.DaggerPokemonComponent
import com.dipizi007.pokedexv20.di.module.ContextModule
import com.dipizi007.pokedexv20.di.module.DaoModule
import com.dipizi007.pokedexv20.di.module.RetrofitApiModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DI.pokemonComponent = DaggerPokemonComponent.builder()
            .contextModule(ContextModule(this))
            .retrofitApiModule(RetrofitApiModule())
            .daoModule(DaoModule())
            .build()
    }
}