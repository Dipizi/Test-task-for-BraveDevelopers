package com.dipizi007.pokedexv20.data.room

import androidx.room.*
import io.reactivex.Completable
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