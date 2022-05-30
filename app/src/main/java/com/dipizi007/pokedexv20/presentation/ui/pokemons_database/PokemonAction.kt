package com.dipizi007.pokedexv20.presentation.ui.pokemons_database

import com.dipizi007.pokedexv20.data.room.PokemonDB

interface PokemonAction {

    fun delete(pokemonDB: PokemonDB)
}