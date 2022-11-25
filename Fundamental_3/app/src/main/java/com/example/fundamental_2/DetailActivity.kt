package com.example.fundamental_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.fundamental_2.adapter.SectionsPagerAdapter
import com.example.fundamental_2.databinding.ActivityDetailBinding
import com.example.fundamental_2.response.DetailResponse
import com.example.fundamental_2.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
        const val EXTRA_USER = "detailActivity"
        const val EXTRA_FRAGMENT = "fragment"
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
        bundle.putString(EXTRA_FRAGMENT,name)
        if (name != null){
            detailViewModel.getDetailUser(name.toString())
        }
        //viewPager
        val sectionsPagerAdapter = SectionsPagerAdapter(this , bundle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun detailSetView(detail : DetailResponse){
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(detail.avatar_url)
                .circleCrop()
                .override(300,300)
                .into(imgDetail)
            bioDetail.text = detail.bio ?: "None Bio"
            followersId.text = detail.followers
            followingId.text = detail.following
            gistsId.text = detail.public_gists
            repoId.text = detail.public_repos
            nameDetail.text = detail.login
            usernameDetail.text = detail.name ?: "None Name"
        }
    }


}