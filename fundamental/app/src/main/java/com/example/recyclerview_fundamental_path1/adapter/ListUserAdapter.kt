package com.example.recyclerview_fundamental_path1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview_fundamental_path1.data.User
import com.example.recyclerview_fundamental_path1.databinding.ItemRowUserBinding

class ListUserAdapter(private val listUser : ArrayList<User>):RecyclerView.Adapter<ListUserAdapter.UserViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack
    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack
    }
    inner class UserViewHolder(val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
//        var imgphoto = binding.userImg
////        var usernames = binding.username
////        var companys = binding.company
////        var followers = binding.follower
////        var followings = binding.following
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userlistbin = listUser[position]
        holder.binding.apply {
            Glide.with(root.context)
                .load(userlistbin.avatar)
                .override(300,200)
                .into(userImg)
            username.text = userlistbin.username
            follower.text = userlistbin.follower
            following.text = userlistbin.following
            root.setOnClickListener {
                onItemClickCallBack.onItemClick(listUser[position])
            }
        }
    }
    interface OnItemClickCallBack{
        fun onItemClick(data:User)
    }
    override fun getItemCount(): Int = listUser.size
}