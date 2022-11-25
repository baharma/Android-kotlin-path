package com.example.fundamental_2.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamental_2.api.ApiConfig
import com.example.fundamental_2.response.ListUserItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    private var _getListItem = MutableLiveData<List<ListUserItems>>()
    val getListItem : LiveData<List<ListUserItems>> = _getListItem

    fun getListFollowing(login : String){
        val client = ApiConfig.getApiService().getFollowing(login)
        client.enqueue(object : Callback<List<ListUserItems>>{
            override fun onResponse(
                call: Call<List<ListUserItems>>,
                response: Response<List<ListUserItems>>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _getListItem.value = responseBody!!
                    }else{
                        Log.e(MotionEffect.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<ListUserItems>>, t: Throwable) {
                Log.e(MotionEffect.TAG, "onFailure: ${t.message}")
            }
        })
    }
}