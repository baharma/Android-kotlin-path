package com.example.fundamental_2

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager

import androidx.activity.viewModels
import com.example.fundamental_2.adapter.MainAdapter
import com.example.fundamental_2.databinding.ActivityMainBinding
import com.example.fundamental_2.response.ListUserItems
import com.example.fundamental_2.response.UserResponse
import com.example.fundamental_2.viewmodel.MainViewModel
import androidx.appcompat.widget.SearchView
import android.widget.SearchView.OnQueryTextListener;
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private var listItems = ArrayList<ListUserItems>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
       mainViewModel.listUserItems.observe(this) { listUserItems ->
           showListUserGit(listUserItems)
       }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint  = resources.getString(R.string.Cari_nama)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(name: String?): Boolean {
                name?.let {

                    mainViewModel.searchGitUser(it)
                    searchView.clearFocus()
                    showListUserGit(listItems)
                }
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(name: String?): Boolean {
                return false
            }
        })
        return true
    }

    private fun showLoading(isLoading : Boolean){
        binding.progressBarMain.visibility = if(isLoading)View.VISIBLE else View.GONE
    }
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.rvGit.windowToken, 0)
    }

    private fun showListUserGit(listUserItems : List<ListUserItems>){
        val  items = ArrayList<ListUserItems>()
        for (user in listUserItems){
            items.clear()
            items.addAll(listUserItems)
        }
        val adapter = MainAdapter(items)

        binding.rvGit.adapter = adapter
        binding.rvGit.setHasFixedSize(true)
        if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.rvGit.layoutManager = GridLayoutManager(this,2)
        }else{
            binding.rvGit.layoutManager = LinearLayoutManager(this)
        }
        adapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ListUserItems) {
                showSelectedUser(data)
            }
        })

    }

    private fun showSelectedUser(data : ListUserItems){
        val intent  = Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER,data.login)
        startActivity(intent)
    }

}