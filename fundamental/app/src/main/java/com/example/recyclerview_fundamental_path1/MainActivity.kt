package com.example.recyclerview_fundamental_path1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_fundamental_path1.DetailActivity.Companion.EXTRA_USER
import com.example.recyclerview_fundamental_path1.adapter.ListUserAdapter
import com.example.recyclerview_fundamental_path1.data.User
import com.example.recyclerview_fundamental_path1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<User>()
    private lateinit var rvuser: RecyclerView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvuser = binding.rvUser
        rvuser.setHasFixedSize(true)
        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(
                    dataUsername[i],
                    dataName[i],
                    dataAvatar.getResourceId(i, -1),
                    dataCompany[i],
                    dataLocation[i],
                    dataRepository[i],
                    dataFollowers[i],
                    dataFollowing[i]
                )
                listUser.add(user)
            }
            dataAvatar.recycle()
            return listUser
        }

    private fun showRecyclerList() {
        rvuser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        rvuser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object :ListUserAdapter.OnItemClickCallBack{
            override fun onItemClick(data: User) {
                showSelectesUser(data)
            }

        })

    }



    private fun showSelectesUser(user:User){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(EXTRA_USER,user)
        startActivity(intent)
        Toast.makeText(this,"User " +user.username,Toast.LENGTH_SHORT).show()
    }
}
