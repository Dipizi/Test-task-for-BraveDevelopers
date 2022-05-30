package com.dipizi007.pokedexv20.domain.use_case.pokemons_databaseUC

import com.dipizi007.pokedexv20.data.room.PokemonDAO
import com.dipizi007.pokedexv20.data.room.PokemonDB
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonsListService @Inject constructor(private val dao: PokemonDAO) {

    fun loadList(): Single<List<PokemonDB>> {
        return dao.showPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deletePokemon(pokemonDB: PokemonDB) {
        dao.deletePokemon(pokemonDB)
    }

    fun searchPokemon(name: String): Single<List<PokemonDB>> {
        return dao.searchPokemon(name)
    }
}