package com.example.pokemon.api

import com.example.pokemon.di.PokemonsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon")
    fun getListName():Call<PokemonsResponse>
}