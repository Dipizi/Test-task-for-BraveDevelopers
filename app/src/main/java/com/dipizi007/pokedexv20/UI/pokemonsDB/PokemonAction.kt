package com.dipizi007.pokedexv20.UI.pokemonsDB

import com.dipizi007.pokedexv20.data.room.PokemonDB

interface PokemonAction {

    fun delete(pokemonDB: PokemonDB)
}