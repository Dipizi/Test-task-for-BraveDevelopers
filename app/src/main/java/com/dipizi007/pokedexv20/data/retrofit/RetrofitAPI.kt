package com.dipizi007.pokedexv20.data.retrofit

import com.dipizi007.pokedexv20.domain.entities.modelPokeApi.ListPokemonsAPI
import com.dipizi007.pokedexv20.domain.entities.modelPokeApi.PokemonInfoAPI
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<ListPokemonsAPI>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: String): Single<PokemonInfoAPI>

}