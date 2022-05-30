package com.dipizi007.pokedexv20.presentation.view_model.pokemons_databaseVM

import androidx.lifecycle.ViewModel
import com.dipizi007.pokedexv20.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListDbVmModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonsListDbViewModel::class)
    abstract fun pokemonListDbViewModel(viewModel: PokemonsListDbViewModel): ViewModel
}