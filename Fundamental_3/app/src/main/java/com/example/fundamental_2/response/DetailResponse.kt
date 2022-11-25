package com.example.fundamental_2.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @field:SerializedName("login")
    val login:String? = null,

    @field:SerializedName("avatar_url")
    val avatar_url:String? = null,

    @field:SerializedName("name")
    val name:String? = null,

    @field:SerializedName("company")
    val company:String? = null,

    @field:SerializedName("bio")
    val bio :String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("public_repos")
    val public_repos: String? = null,

    @field:SerializedName("public_gists")
    val public_gists: String? = null,

    @field:SerializedName("followers")
    val followers:String? = null,

    @field:SerializedName("following")
    val following:String? = null
)
