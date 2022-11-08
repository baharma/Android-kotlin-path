package com.example.pokemon.di

import com.google.gson.annotations.SerializedName

data class PokemonsResponse(
    @field:SerializedName("results")
    val results : List<ResultNamePokemon>
)
data class ResultNamePokemon(
    @field:SerializedName("name")
    val name: String
)
