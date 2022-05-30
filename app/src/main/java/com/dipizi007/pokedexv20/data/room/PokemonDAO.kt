package com.dipizi007.pokedexv20.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon_table")
    fun showPokemon(): Single<List<PokemonDB>>

    @Query("SELECT * FROM pokemon_table WHERE name LIKE :name")
    fun searchPokemon(name: String): Single<List<PokemonDB>>

    @Insert
    fun createPokemon(pokemonDB: PokemonDB)

    @Delete
    fun deletePokemon(pokemonDB: PokemonDB)
}