package com.dipizi007.pokedexv20.utils

sealed class Results<T>

class PendingResults<T> : Results<T>()

class SuccessResults<T>(
    val response: T
) : Results<T>()

class ErrorResults<T>(
    val exception: Exception
) : Results<T>()
