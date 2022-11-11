package com.example.fundamental_2.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamental_2.api.ApiConfig
import com.example.fundamental_2.response.ListUserItems
import com.example.fundamental_2.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val isLoading  : LiveData<Boolean> =  _loading

    private val _listUserGit = MutableLiveData<ArrayList<ListUserItems>>()
    val listUserItems : MutableLiveData<ArrayList<ListUserItems>> = _listUserGit



     fun searchGitUser(name : String){
        _loading.value = true
        val client = ApiConfig.getApiService().searchUser(name)
        client.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _loading.value = false
                if (response.isSuccessful){
                    _listUserGit.value = response.body()?.items as ArrayList<ListUserItems>?

                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}