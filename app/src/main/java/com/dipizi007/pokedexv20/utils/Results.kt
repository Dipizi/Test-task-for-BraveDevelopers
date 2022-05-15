package com.dipizi007.pokedexv20.data.result

sealed class Results<T>

class PendingResults<T> : Results<T>()

class SuccessResults<T>(
    val response: T
) : Results<T>()

class ErrorResults<T>(
    val exception: Exception
) : Results<T>()
