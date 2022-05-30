package com.dipizi007.pokedexv20.presentation.ui.pokemons_random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dipizi007.pokedexv20.DI
import com.dipizi007.pokedexv20.R
import com.dipizi007.pokedexv20.presentation.view_model.pokemons_randomVM.PokemonRandomViewModel
import com.dipizi007.pokedexv20.data.room.PokemonDB
import com.dipizi007.pokedexv20.databinding.FragmentRandomPokemonBinding
import com.dipizi007.pokedexv20.utils.ErrorResults
import com.dipizi007.pokedexv20.utils.PendingResults
import com.dipizi007.pokedexv20.utils.SuccessResults


class PokemonRandomFragment : Fragment() {

    lateinit var binding: FragmentRandomPokemonBinding

    private val viewModel: PokemonRandomViewModel by viewModels { DI.pokemonComponent.getViewModelFactory() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddBD.setOnClickListener {
            val currentPokemon = viewModel.pokemon.value as SuccessResults
            val name = currentPokemon.response.name
            val icon = currentPokemon.response.image
            val pokemon = PokemonDB(id = 0, name = name, icon = icon)
            viewModel.addPokemon(pokemon)
        }

        binding.btnRandom.setOnClickListener {
            viewModel.loadingPokemon()
        }

        binding.tryAgainButton.setOnClickListener {
            if (viewModel.listName.value != null)
                viewModel.loadingPokemon()
            else viewModel.loadListPokemons()
        }

        viewModel.pokemon.observe(viewLifecycleOwner) {
            when (it) {
                is PendingResults -> {
                    with(binding) {
                        progressBar.visibility = View.VISIBLE
                        errorContainer.visibility = View.GONE
                        ivIconPok.visibility = View.GONE
                        btnRandom.isEnabled = false
                        btnAddBD.isEnabled = false
                        tvName.visibility = View.GONE
                    }
                }

                is ErrorResults -> {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        errorContainer.visibility = View.VISIBLE
                        ivIconPok.visibility = View.GONE
                        btnRandom.visibility = View.GONE
                        btnAddBD.visibility = View.GONE
                        tvName.visibility = View.GONE
                    }
                }

                is SuccessResults -> {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        errorContainer.visibility = View.GONE
                        ivIconPok.visibility = View.VISIBLE
                        btnRandom.isEnabled = true
                        btnAddBD.isEnabled = true
                        btnAddBD.visibility = View.VISIBLE
                        btnRandom.visibility = View.VISIBLE
                        tvName.visibility = View.VISIBLE

                        tvName.text = it.response.name
                        Glide
                            .with(requireActivity())
                            .load(it.response.image)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(ivIconPok)
                    }
                }
            }
        }
    }
}


