package com.example.fundamental_2.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamental_2.api.ApiConfig
import com.example.fundamental_2.response.ListUserItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowersViewModel : ViewModel() {
    private val _listFollower = MutableLiveData<List<ListUserItems>>()
    val listFollower: LiveData<List<ListUserItems>> = _listFollower


    fun getDataFollowers(login : String){
        ApiConfig.getApiService().getFollowers(login).enqueue(object : Callback<List<ListUserItems>>{
            override fun onResponse(
                call: Call<List<ListUserItems>>,
                response: Response<List<ListUserItems>>
            ) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listFollower.value = responseBody!!
                    }
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListUserItems>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


}