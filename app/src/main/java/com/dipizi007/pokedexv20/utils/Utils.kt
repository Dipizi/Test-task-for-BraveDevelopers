package com.dipizi007.pokedexv20.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = viewModels[modelClass] ?: throw IllegalStateException("Unknown ViewModel")
        return viewModel.get() as T
    }
}

fun String.upFirst(): String {
    return this[0].uppercase() + this.substring(1, this.length)
}
