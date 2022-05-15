package com.dipizi007.pokedexv20.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [PokemonDB::class]
)
abstract class AppDB : RoomDatabase() {

    abstract fun getPokemonsDAO(): PokemonDAO
}