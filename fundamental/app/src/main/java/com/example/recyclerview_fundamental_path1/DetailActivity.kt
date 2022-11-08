package com.example.recyclerview_fundamental_path1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.recyclerview_fundamental_path1.data.User
import com.example.recyclerview_fundamental_path1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val data = intent.getParcelableExtra<User>(EXTRA_USER) as User

        binding.apply {
            Glide.with(root.context)
                .load(data.avatar)
                .circleCrop()
                .override(300,200)
                .into(imgDt)
            repositoryDt.text = data.repository
            usernameDt.text = data.username
            descriptionDt.text = data.location
            followingDt.text = data.following
            followerDt.text = data.follower
            companyDt.text = data.company
        }
    }

    companion object{

        const val EXTRA_USER = "extra_user"
    }
}