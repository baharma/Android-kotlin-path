package com.example.fundamental_3_new.data.remote.api



import com.example.fundamental_3_new.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getSearch(
        @Path("q") login : String
    )

}