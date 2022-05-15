package com.dipizi007.pokedexv20.UI.pokemonsDB

import com.dipizi007.pokedexv20.data.room.PokemonDB
import com.dipizi007.pokedexv20.data.room.PokemonDaoImpl
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonsListService(private val pokemonDaoImpll: PokemonDaoImpl) {

    fun loadList(): Single<List<PokemonDB>> {
        return pokemonDaoImpll.showPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deletePokemon(pokemonDB: PokemonDB) {
        pokemonDaoImpll.deletePokemon(pokemonDB)
    }

    fun searchPokemon(name: String): Single<List<PokemonDB>> {
        return pokemonDaoImpll.searchPokemon(name)
    }
}