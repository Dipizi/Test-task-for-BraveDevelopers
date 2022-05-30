package com.dipizi007.pokedexv20.presentation.view_model.pokemons_randomVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dipizi007.pokedexv20.data.room.PokemonDAO
import com.dipizi007.pokedexv20.data.room.PokemonDB
import com.dipizi007.pokedexv20.domain.use_case.pokemons_randomUC.Listener
import com.dipizi007.pokedexv20.domain.use_case.pokemons_randomUC.PokemonRandomService
import com.dipizi007.pokedexv20.domain.entities.Pokemon
import com.dipizi007.pokedexv20.domain.entities.modelPokeApi.ListPokemonsAPI
import com.dipizi007.pokedexv20.domain.entities.modelPokeApi.PokemonAPI
import com.dipizi007.pokedexv20.utils.*
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

typealias LiveResult<T> = LiveData<Results<T>>
typealias MutableLiveResult<T> = MutableLiveData<Results<T>>

class PokemonRandomViewModel @Inject constructor(
    private val pokemonRandomService: PokemonRandomService,
    private val pokemonDaoImpl: PokemonDAO
) : ViewModel() {

    private val _pokemon = MutableLiveResult<Pokemon>(PendingResults())
    val pokemon: LiveResult<Pokemon> = _pokemon

    private val _listName = MutableLiveData<List<PokemonAPI>>()
    val listName: LiveData<List<PokemonAPI>> = _listName

    private val listener: Listener = {
        _listName.value = it
    }

    init {
        pokemonRandomService.addListener(listener)
        loadListPokemons()
    }

    fun loadListPokemons() {
        pokemonRandomService.responseListPokemonAPI()
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
        pokemonRandomService.responseIconPokemonAPI()
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
        pokemonRandomService.removeListener(listener)
    }
}