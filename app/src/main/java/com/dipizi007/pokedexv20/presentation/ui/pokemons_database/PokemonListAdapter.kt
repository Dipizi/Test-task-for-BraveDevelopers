package com.dipizi007.pokedexv20.presentation.ui.pokemons_database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dipizi007.pokedexv20.R
import com.dipizi007.pokedexv20.data.room.PokemonDB
import com.dipizi007.pokedexv20.databinding.PokemonListItemBinding

class PokemonListAdapter(private val pokemonAction: PokemonAction) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>(), View.OnClickListener {

    var pokemonList = listOf<PokemonDB>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindnig = PokemonListItemBinding.inflate(inflater, parent, false)
        bindnig.ivDelete.setOnClickListener(this)
        return ViewHolder(bindnig)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        with(holder.binding) {
            ivDelete.tag = pokemon
            tvName.text = pokemon.name
            Glide
                .with(ivPokemon.context)
                .load(pokemon.icon)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivPokemon)
        }

    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onClick(p0: View) {
        val pokemon = p0.tag as PokemonDB
        when (p0.id) {
            R.id.ivDelete -> {
                pokemonAction.delete(pokemon)
            }
        }
    }
}