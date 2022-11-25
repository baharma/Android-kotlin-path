package com.example.fundamental_2.api


import com.example.fundamental_2.BuildConfig
import com.example.fundamental_2.response.DetailResponse
import com.example.fundamental_2.response.FollowersResponse
import com.example.fundamental_2.response.ListUserItems
import com.example.fundamental_2.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun searchUser(
        @Query("q") login: String
    ):Call<UserResponse>


    @GET("users/{login}")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getDetailUser(
        @Path("login") login: String
    ):Call<DetailResponse>

    @GET("users/{login}/followers")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowers(
        @Path("login") login: String
    ):Call<List<ListUserItems>>

    @GET("users/{login}/following")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowing(
        @Path("login") login: String
    ):Call<List<ListUserItems>>

}