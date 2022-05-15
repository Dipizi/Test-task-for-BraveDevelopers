package com.dipizi007.pokedexv20.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemon_table"
)
data class PokemonDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val icon: String
)