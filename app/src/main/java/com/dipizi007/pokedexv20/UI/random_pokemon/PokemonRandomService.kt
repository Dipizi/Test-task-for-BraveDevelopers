package com.dipizi007.pokedexv20.UI.random_pokemon

import com.dipizi007.pokedexv20.data.retrofit.RetrofitInstance
import com.dipizi007.pokedexv20.model.modelPokeApi.ListPokemonsAPI
import com.dipizi007.pokedexv20.model.modelPokeApi.PokemonAPI
import com.dipizi007.pokedexv20.model.Pokemon
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val LIMIT = 1126
private const val OFFSET = 0

typealias Listener = (List<PokemonAPI>) -> Unit

class PokemonListService {

    private val retrofit = RetrofitInstance.getRetrofitAPI
    private val list = mutableListOf<PokemonAPI>()
    private val listeners = mutableSetOf<Listener>()

    fun responseIconPokemonAPI(): Single<Pokemon> {
        val name = list.random().name
        return retrofit.getPokemon(name)
            .subscribeOn(Schedulers.io())
            .map { Pokemon(it.name, it.sprites.other.official_artwork.front_default) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun responseListPokemonAPI(): Single<ListPokemonsAPI> {
        return retrofit.getPokemonList(LIMIT, OFFSET)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list.addAll(it.results) }
    }

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }
}
