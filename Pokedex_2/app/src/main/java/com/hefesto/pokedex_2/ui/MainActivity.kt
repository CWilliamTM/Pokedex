package com.hefesto.pokedex_2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hefesto.pokedex_2.R
import android.content.Intent
import android.view.View
import com.google.android.libraries.places.api.Places
import com.hefesto.pokedex_2.BuildConfig
import com.hefesto.pokedex_2.data.AppDatabase
import com.hefesto.pokedex_2.data.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val pokemonlist: MutableList<Pokemon> = mutableListOf()
    private var adapter: PokemonAdapter = PokemonAdapter(pokemonlist){
        Intent(this, PokemonDetailActivity::class.java).apply {
            putExtra(PokemonDetailActivity.POKEMON_EXTRA, it)
        }.also {
            startActivity(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Places.initialize(applicationContext, BuildConfig.GOOGLE_API_KEY)
        setUpPokemonListWithRecyclerView()
        setupAddPokemonButtonClick()
        shouldDisplayEmptyView()
    }

    private fun setUpPokemonListWithRecyclerView(){
        rvPokemons.adapter = adapter
    }

    private fun setupAddPokemonButtonClick(){
        fabAddPokemon.setOnClickListener {
            startAddPokemonActivityForNewPokemon()
        }
    }

    private fun startAddPokemonActivityForNewPokemon(){
        Intent(this, AddPokemonActivity::class.java).also {
            //startActivity(it)
            startActivityForResult(it, ADD_POKEMON_REQUEST_CODE)
        }
    }

    private fun shouldDisplayEmptyView() {
        emptyView.visibility = if (pokemonlist.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onResume() {
        super.onResume()
        val updatedPokemonList = AppDatabase.getInstance(this).pokemonDao.selectAll()
        updateRecyclerView(updatedPokemonList)
    }

    private fun updateRecyclerView(updatedPokemonList: List<Pokemon>){
        pokemonlist.clear()
        pokemonlist.addAll(updatedPokemonList)
        adapter.notifyDataSetChanged()
        shouldDisplayEmptyView()
    }

    companion object{
        const val ADD_POKEMON_REQUEST_CODE = 1
    }
}