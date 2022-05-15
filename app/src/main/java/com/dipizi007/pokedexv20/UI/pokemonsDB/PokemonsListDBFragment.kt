package com.dipizi007.pokedexv20.UI.pokemonsDB

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dipizi007.pokedexv20.data.room.PokemonDB
import com.dipizi007.pokedexv20.databinding.FragmentPokemonsListDbBinding
import com.dipizi007.pokedexv20.utils.factory

class PokemonsListDBFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var bindnig: FragmentPokemonsListDbBinding
    lateinit var adapter: PokemonListAdapter

    private val viewModel: PokemonsListDbViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindnig = FragmentPokemonsListDbBinding.inflate(inflater, container, false)
        return bindnig.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PokemonListAdapter(object : PokemonAction {
            override fun delete(pokemonDB: PokemonDB) {
                viewModel.deletePokemon(pokemonDB)
            }
        })

        bindnig.rcPokemonsDB.adapter = adapter
        bindnig.rcPokemonsDB.layoutManager = LinearLayoutManager(context)

        viewModel.loadList()

        viewModel.listDB.observe(viewLifecycleOwner) {
            adapter.pokemonList = it

        }

        bindnig.svPokemon.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (query != "")
            searchDataBase(query)
        else
            viewModel.loadList()
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        if (newText != "")
            searchDataBase(newText)
        else
            viewModel.loadList()
        return true
    }

    private fun searchDataBase(query: String){
        val queryDB = "%$query%"
        viewModel.searchPokemon(queryDB)
    }
}