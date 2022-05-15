package com.dipizi007.pokedexv20.data.retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.create

object RetrofitInstance {

    val getRetrofitAPI: RetrofitAPI
        get() = retrofitInstance().create(RetrofitAPI::class.java)

    private fun retrofitInstance(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit
    }
}