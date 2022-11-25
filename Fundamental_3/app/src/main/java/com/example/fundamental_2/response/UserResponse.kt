package com.example.fundamental_2.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponse (
    @field:SerializedName("items")
    val items: List<ListUserItems>
        )

@Parcelize
data class ListUserItems(
        @field:SerializedName("login")
        val login : String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("avatar_url")
        val avatar_url :String,

        @field:SerializedName("url")
        val url : String
): Parcelable