package com.dipizi007.pokedexv20.UI.random_pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dipizi007.pokedexv20.data.result.ErrorResults
import com.dipizi007.pokedexv20.data.result.PendingResults
import com.dipizi007.pokedexv20.data.result.Results
import com.dipizi007.pokedexv20.data.result.SuccessResults
import com.dipizi007.pokedexv20.data.room.PokemonDB
import com.dipizi007.pokedexv20.data.room.PokemonDaoImpl
import com.dipizi007.pokedexv20.model.modelPokeApi.ListPokemonsAPI
import com.dipizi007.pokedexv20.model.modelPokeApi.PokemonAPI
import com.dipizi007.pokedexv20.model.Pokemon
import com.dipizi007.pokedexv20.utils.upFirst
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

typealias LiveResult<T> = LiveData<Results<T>>
typealias MutableLiveResult<T> = MutableLiveData<Results<T>>

class PokemonListViewModel(
    private val pokemonListService: PokemonListService,
    private val pokemonDaoImpl: PokemonDaoImpl
) : ViewModel() {

    private val _pokemon = MutableLiveResult<Pokemon>(PendingResults())
    val pokemon: LiveResult<Pokemon> = _pokemon

    private val _listName = MutableLiveData<List<PokemonAPI>>()
    val listName: LiveData<List<PokemonAPI>> = _listName

    private val listener: Listener = {
        _listName.value = it
    }

    init {
        pokemonListService.addListener(listener)
        loadListPokemons()
    }

    fun loadListPokemons() {
        pokemonListService.responseListPokemonAPI()
            .subscribe(object : SingleObserver<ListPokemonsAPI> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: ListPokemonsAPI) {
                    if (_listName.value?.size == null) {
                        loadingPokemon()
                    }
                }

                override fun onError(e: Throwable) {
                    _pokemon.value = ErrorResults(e as Exception)
                }
            })
    }

    fun loadingPokemon() {
        _pokemon.value = PendingResults()
        pokemonListService.responseIconPokemonAPI()
            .subscribe(object : SingleObserver<Pokemon> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: Pokemon) {
                    _pokemon.value = SuccessResults(Pokemon(t.name.upFirst(), t.image))
                }

                override fun onError(e: Throwable) {
                    _pokemon.value = ErrorResults(e as Exception)
                }
            })
    }

    fun addPokemon(pokemonDB: PokemonDB) {
        Single.fromCallable { pokemonDB }
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<PokemonDB> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: PokemonDB) {
                    pokemonDaoImpl.createPokemon(t)
                }

                override fun onError(e: Throwable) {}
            })
    }

    override fun onCleared() {
        super.onCleared()
        pokemonListService.removeListener(listener)
    }
}