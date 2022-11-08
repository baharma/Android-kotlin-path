package com.example.pokemon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Adapater.MainAdapter
import com.example.pokemon.api.ApiConfig
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.di.PokemonsResponse
import com.example.pokemon.di.ResultNamePokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetail()

    }
   private fun getDetail(){
       ApiConfig.getApiService().getListName().enqueue(object :Callback<PokemonsResponse>{
           @SuppressLint("NotifyDataSetChanged")
           override fun onResponse(
               call: Call<PokemonsResponse>,
               response: Response<PokemonsResponse>
           ) {
                val responseBody = response.body()
                val dataPokemon = responseBody?.results
                val adapaterPokemin = MainAdapter(dataPokemon)
                binding.rvPokemon.apply {
                    layoutManager= LinearLayoutManager(this@MainActivity)
                    setHasFixedSize(true)
                    adapaterPokemin.notifyDataSetChanged()
                    adapter = adapaterPokemin
                }
           }

           override fun onFailure(call: Call<PokemonsResponse>, t: Throwable) {
               Log.d("Failure dude", t.message!!)
           }
       })
   }




}