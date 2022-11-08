package com.example.pokemon.Adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.RowListPokemonBinding
import com.example.pokemon.di.PokemonsResponse
import com.example.pokemon.di.ResultNamePokemon

class MainAdapter(private val listPokemon: List<ResultNamePokemon?>?):RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowListPokemonBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(listName : ResultNamePokemon){
            binding.textView.text = listName.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowListPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPokemon!![position]!!)
    }

    override fun getItemCount(): Int {
        return listPokemon!!.size
    }
}