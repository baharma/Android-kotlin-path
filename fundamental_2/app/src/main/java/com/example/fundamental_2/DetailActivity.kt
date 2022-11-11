package com.example.fundamental_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.fundamental_2.databinding.ActivityDetailBinding
import com.example.fundamental_2.response.DetailResponse
import com.example.fundamental_2.response.ListUserItems
import com.example.fundamental_2.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    companion object{
        const val EXTRA_USER = "detailActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetailUser()

        detailViewModel.detailResponse.observe(this){detail->
            detailSetView(detail)
        }

    }
    private fun setDetailUser(){

    val name  = intent.getStringExtra(EXTRA_USER)
    val bundle = Bundle()
        bundle.putString(EXTRA_USER,name)
        if (name != null){
            detailViewModel.getDetailUser(name.toString())
        }

    }

    private fun detailSetView(detail : DetailResponse){
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(detail.avatar_url)
                .circleCrop()
                .override(300,300)
                .into(imgDetail)
            bioDetail.text = detail.bio
            followersId.text = detail.followers
            followingId.text = detail.following
            gistsId.text = detail.public_gists
            repoId.text = detail.public_repos
            nameDetail.text = detail.login
            usernameDetail.text = detail.name
        }
    }


}