package com.dipizi007.pokedexv20.di


import com.dipizi007.pokedexv20.presentation.view_model.pokemons_databaseVM.ListDbVmModule
import com.dipizi007.pokedexv20.presentation.view_model.pokemons_randomVM.RandomVmModule
import com.dipizi007.pokedexv20.data.retrofit.RetrofitAPI
import com.dipizi007.pokedexv20.di.module.RetrofitApiModule
import com.dipizi007.pokedexv20.di.module.DaoModule
import com.dipizi007.pokedexv20.utils.ViewModelFactory
import com.dipizi007.pokedexv20.di.module.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RetrofitApiModule::class,
        RandomVmModule::class,
        ViewModelFactoryModule::class,
        DaoModule::class,
        ListDbVmModule::class
    ]
)
@Singleton
interface PokemonComponent {

    fun getRetrofitApi(): RetrofitAPI

    fun getViewModelFactory(): ViewModelFactory
}


