package com.hefesto.pokedex_2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.pokedex_2.R
import com.hefesto.pokedex_2.data.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_pokemon.view.*

class PokemonAdapter(
    private val pokemons: List<Pokemon>,
    private val onItemClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>(){

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItemView(pokemon: Pokemon, onItemClick: (Pokemon) -> Unit){
            with(itemView){
                tvPokemonName.text = pokemon.name
                tvPokemonNumber.text = "#%03d".format(pokemon.number)
                Picasso.get().load(pokemon.imageUrl).into(ivPokemonImage)
                setOnClickListener {
                    onItemClick(pokemon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_pokemon, parent, false)
            .let {
                PokemonViewHolder(it)
            }

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(
        holder: PokemonViewHolder,
        position: Int
    ) {
        holder.bindItemView(pokemons[position], onItemClick)
    }
}