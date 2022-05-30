package com.dipizi007.pokedexv20.presentation.view_model.pokemons_randomVM

import androidx.lifecycle.ViewModel
import com.dipizi007.pokedexv20.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RandomVmModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonRandomViewModel::class)
    abstract fun pokemonRandomViewModel(viewModel: PokemonRandomViewModel): ViewModel

}