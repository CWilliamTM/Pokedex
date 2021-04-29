package com.hefesto.pokedex_2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hefesto.pokedex_2.R
import com.hefesto.pokedex_2.data.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        bindViewsToPokemonData()
    }

    private fun bindViewsToPokemonData(){
        intent.getParcelableExtra<Pokemon>(POKEMON_EXTRA)?.let {
            tvName.text = it.name
            tvNumber.text = "#%03d".format(it.number)
            bindTextViewsToPokemonTypes(it)
            tvWeight.text = "%.1f kg".format(it.weight)
            tvHeight.text = "%.2f cm".format(it.height / 10)
            Picasso.get().load(it.imageUrl).into(ivImage)
            bindMapFragmentToPokemonLocation(it)
        }
    }

    private fun bindTextViewsToPokemonTypes(pokemon: Pokemon){
        tvFirstType.text = pokemon.types.getOrNull(0)
        val secondType = pokemon.types.getOrNull(1)
        if(secondType == null){
            tvSecondType.visibility = View.GONE
        }
        else{
            tvSecondType.visibility = View.VISIBLE
            tvSecondType.text = secondType
        }
    }

    private fun bindMapFragmentToPokemonLocation(pokemon: Pokemon){
        (supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment).apply {
            getMapAsync{googleMap ->
                googleMap.uiSettings.isZoomControlsEnabled = true

                val latLng = LatLng(pokemon.latitude, pokemon.longitude)
                MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                        .also {
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
                        }
            }
        }
    }

    companion object{
        val POKEMON_EXTRA = "${PokemonDetailActivity::class.java.`package`}.showPokemonDetail"
    }
}