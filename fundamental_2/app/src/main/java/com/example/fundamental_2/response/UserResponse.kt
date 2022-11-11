package com.example.fundamental_2.response

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @field:SerializedName("items")
    val items: List<ListUserItems>
        )


data class ListUserItems(
        @field:SerializedName("login")
        val login : String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("avatar_url")
        val avatar_url :String,

        @field:SerializedName("url")
        val url : String
)