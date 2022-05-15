package com.dipizi007.pokedexv20.UI.pokemonsDB

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dipizi007.pokedexv20.data.room.PokemonDB
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PokemonsListDbViewModel(private val pokemonsListService: PokemonsListService) : ViewModel() {

    private val _listDB = MutableLiveData<List<PokemonDB>>()
    val listDB: LiveData<List<PokemonDB>> = _listDB

    fun loadList() {
        pokemonsListService.loadList()
            .subscribe(object : SingleObserver<List<PokemonDB>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: List<PokemonDB>) {
                    _listDB.value = t
                }

                override fun onError(e: Throwable) {}
            })
    }

    fun deletePokemon(pokemonDB: PokemonDB) {
        Single.fromCallable { pokemonDB }
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<PokemonDB> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: PokemonDB) {
                    pokemonsListService.deletePokemon(pokemonDB)
                    loadList()
                }

                override fun onError(e: Throwable) {}
            })
    }

    fun searchPokemon(name: String) {
        pokemonsListService.searchPokemon(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<PokemonDB>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(t: List<PokemonDB>) {
                    _listDB.value = t
                }

                override fun onError(e: Throwable) {}
            })
    }
}