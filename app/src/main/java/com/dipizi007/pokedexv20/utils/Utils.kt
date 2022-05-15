package com.dipizi007.pokedexv20.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dipizi007.pokedexv20.App
import com.dipizi007.pokedexv20.UI.pokemonsDB.PokemonsListDbViewModel
import com.dipizi007.pokedexv20.UI.random_pokemon.PokemonListViewModel
import java.lang.IllegalStateException

class ViewModelFactory(private val app: App) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            PokemonListViewModel::class.java -> {
                PokemonListViewModel(
                    app.pokemonListService,
                    app.pokemonDaoImpl
                )
            }
            PokemonsListDbViewModel::class.java -> {
                PokemonsListDbViewModel(app.pokemonsListService)
            }
            else -> {
                throw IllegalStateException("Unknown ViewModel")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun String.upFirst(): String {
    return this[0].uppercase() + this.substring(1, this.length)
}
