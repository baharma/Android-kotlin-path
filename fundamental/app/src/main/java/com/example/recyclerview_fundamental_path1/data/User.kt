package com.example.recyclerview_fundamental_path1.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(

    var username :String,
    var name : String,
    var avatar: Int,
    var company:String,
    var location:String,
    var repository:String,
    var follower:String,
    var following:String,
): Parcelable
