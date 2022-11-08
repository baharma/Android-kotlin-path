package com.example.pokemon

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.ApiConfig
import com.example.pokemon.di.PokemonsResponse
import com.example.pokemon.di.ResultNamePokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
     val listPokemon = MutableLiveData<List<ResultNamePokemon>>()

    fun getNameList(){
        val client = ApiConfig.getApiService().getListName()
        client.enqueue(object :Callback<PokemonsResponse>{
            override fun onResponse(
                call: Call<PokemonsResponse>,
                response: Response<PokemonsResponse>
            ) {
                if(response.isSuccessful){
                    listPokemon.postValue(response.body()?.results)
                }else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<PokemonsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getList():LiveData<List<ResultNamePokemon>>{
        return listPokemon
    }

}