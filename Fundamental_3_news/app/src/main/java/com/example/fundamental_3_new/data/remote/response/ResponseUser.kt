package com.example.fundamental_3_new.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseUser (
        @field:SerializedName("total_count")
        val total_count : Int ,

        @field:SerializedName("incomplete_results")
        val incomplete_results : Boolean,

        @field:SerializedName("listUser")
        val listUser : List<ResponseUserList>
        )

data class ResponseUserList(
        @field:SerializedName("login")
        val login: String,

        @field:SerializedName("avatar_url")
        val avatar_url: String,

        @field:SerializedName("html_url")
        val html_url: String,
)