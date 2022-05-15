package com.dipizi007.pokedexv20

import android.app.Application
import androidx.room.Room
import com.dipizi007.pokedexv20.UI.pokemonsDB.PokemonsListService
import com.dipizi007.pokedexv20.UI.random_pokemon.PokemonListService
import com.dipizi007.pokedexv20.data.room.AppDB
import com.dipizi007.pokedexv20.data.room.PokemonDaoImpl

class App: Application() {

    val pokemonListService = PokemonListService()
    lateinit var pokemonsListService: PokemonsListService
    lateinit var pokemonDaoImpl: PokemonDaoImpl
    lateinit var dataBase: AppDB


    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(applicationContext, AppDB::class.java, "database-pok").build()
        val dao = dataBase.getPokemonsDAO()
        pokemonDaoImpl = PokemonDaoImpl(dao)
        pokemonsListService = PokemonsListService(pokemonDaoImpl)
    }
}