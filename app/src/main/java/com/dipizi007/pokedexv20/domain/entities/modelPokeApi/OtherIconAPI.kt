package com.dipizi007.pokedexv20.domain.entities.modelPokeApi

import com.google.gson.annotations.SerializedName

data class OtherIconAPI(
    @SerializedName("official-artwork")
    val official_artwork: OfficialArtWorkAPI
)
