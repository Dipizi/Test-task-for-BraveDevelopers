package com.dipizi007.pokedexv20.data.room

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PokemonDaoImpl(private val dao: PokemonDAO) : PokemonDAO {


    override fun showPokemon(): Single<List<PokemonDB>> {
        return dao.showPokemon()
    }

    override fun searchPokemon(name: String): Single<List<PokemonDB>> {
        return dao.searchPokemon(name)
    }

    override fun createPokemon(pokemonDB: PokemonDB) {
        dao.createPokemon(pokemonDB)
    }

    override fun deletePokemon(pokemonDB: PokemonDB) {
        dao.deletePokemon(pokemonDB)
    }
}