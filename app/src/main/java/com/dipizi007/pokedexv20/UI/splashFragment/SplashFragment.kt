package com.dipizi007.pokedexv20.UI.splashFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.dipizi007.pokedexv20.R
import com.dipizi007.pokedexv20.databinding.FragmentSplashBinding
import io.reactivex.Single
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler(Looper.getMainLooper())
        Thread {
            handler.postDelayed({ findNavController().navigate(R.id.pokemonListFragment) }, 2000)
        }.start()

    }


}