package com.hefesto.pokedexnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_pokemon.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pokemonslist = listOf("Pikachu", "Voltorb", "Pikachu", "Voltorb")
        rvPokemons.adapter = PokemonAdapter(pokemonslist)
        shouldDisplayEmptyView(pokemonslist.isEmpty())
    }

    fun shouldDisplayEmptyView(isEmpty: Boolean) {
        emptyView.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    class PokemonAdapter(private val pokemons: List<String>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
        class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pokemon, parent, false)
            return PokemonViewHolder(itemView)
        }

        override fun getItemCount() = pokemons.size

        override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
            holder.itemView.tvPokemonName.text = pokemons[position]
        }
    }
}

/*
package com.hefesto.mycalculator

import kotlin.math.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var txt_valor_1: EditText? = null
    private var txt_valor_2: EditText? = null
    private var txt_resultado: TextView? = null
    private var rb1: RadioButton? = null
    private var rb2: RadioButton? = null
    private var rb3: RadioButton? = null
    private var rb4: RadioButton? = null
    private var rb5: RadioButton? = null
    private var rb6: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_valor_1 = findViewById(R.id.txt_valor1) as EditText
        txt_valor_2 = findViewById(R.id.txt_valor2) as EditText
        txt_resultado = findViewById(R.id.txt_result) as TextView
        rb1 = findViewById(R.id.rb_oper1) as RadioButton
        rb2 = findViewById(R.id.rb_oper2) as RadioButton
        rb3 = findViewById(R.id.rb_oper3) as RadioButton
        rb4 = findViewById(R.id.rb_oper4) as RadioButton
        rb5 = findViewById(R.id.rb_oper5) as RadioButton
        rb6 = findViewById(R.id.rb_oper6) as RadioButton
    }

    fun calcular(view: View?){
        val valor1: Double
        val valor2: Double
        var resultado: String
        valor1 = txt_valor_1!!.text.toString().toDouble()
        valor2 = txt_valor_2!!.text.toString().toDouble()
        if (rb1!!.isChecked) {
            resultado = valor1.toString() + " + " + valor2.toString() + " = "
            resultado += String.format("%.2f" , (valor1 + valor2))
            txt_resultado!!.text = resultado
        } else if (rb2!!.isChecked) {
            resultado = valor1.toString() + " - " + valor2.toString() + " = "
            resultado += String.format("%.2f" , (valor1 - valor2))
            txt_resultado!!.text = resultado
        } else if (rb3!!.isChecked) {
            resultado = valor1.toString() + " * " + valor2.toString() + " = "
            resultado += String.format("%.2f" , (valor1 * valor2))
            txt_resultado!!.text = resultado
        } else if (rb4!!.isChecked) {
            if (valor2 != 0.0) {
                resultado = valor1.toString() + " / " + valor2.toString() + " = "
                resultado += String.format("%.2f" , (valor1 / valor2))
                txt_resultado!!.text = resultado
            } else {
                Toast.makeText(this, "Divisão não permitida", Toast.LENGTH_SHORT).show()
            }
        } else if (rb5!!.isChecked) {
            resultado = valor1.toString() + " % " + valor2.toString() + " = "
            resultado += String.format("%.2f" , (valor1 % valor2))
            txt_resultado!!.text = resultado
        } else if (rb6!!.isChecked) {
            resultado = valor1.toString() + " ^ " + valor2.toString() + " = "
            resultado += String.format("%.2f" , (valor1.pow(valor2)))
            txt_resultado!!.text = resultado
        }
    }
}

<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#EFEFEF" />
    <corners android:radius="16dp" />
</shape>
*/