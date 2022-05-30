package com.dipizi007.pokedexv20.domain.use_case.pokemons_randomUC

import com.dipizi007.pokedexv20.data.retrofit.RetrofitAPI
import com.dipizi007.pokedexv20.domain.entities.Pokemon
import com.dipizi007.pokedexv20.domain.entities.modelPokeApi.ListPokemonsAPI
import com.dipizi007.pokedexv20.domain.entities.modelPokeApi.PokemonAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val LIMIT = 1126
private const val OFFSET = 0

typealias Listener = (List<PokemonAPI>) -> Unit

class PokemonRandomService @Inject constructor(private val retrofit: RetrofitAPI) {

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
