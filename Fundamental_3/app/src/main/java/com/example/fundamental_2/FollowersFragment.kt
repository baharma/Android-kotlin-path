package com.example.fundamental_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamental_2.adapter.MainAdapter
import com.example.fundamental_2.databinding.FragmentFollowersBinding
import com.example.fundamental_2.response.ListUserItems
import com.example.fundamental_2.viewmodel.FollowersViewModel


class FollowersFragment : Fragment() {
    private var _binding : FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var followersViewModel: FollowersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        followersViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followersViewModel.getDataFollowers(arguments?.getString(DetailActivity.EXTRA_FRAGMENT).toString())

        followersViewModel.listFollower.observe(viewLifecycleOwner) { listFollower ->
            Log.e("test", "$listFollower")
            setDataToFragment(listFollower)
        }
    }
    private fun setDataToFragment(listFollower: List<ListUserItems>) {
        val listUser = ArrayList<ListUserItems>()
        with(binding) {
            for (user in listFollower) {
                listUser.clear()
                listUser.addAll(listFollower)
            }
            rvFollowers.layoutManager = LinearLayoutManager(context)
            val adapter = MainAdapter(listUser)
            rvFollowers.adapter = adapter

         adapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
             override fun onItemClicked(data: ListUserItems) {
                 showSelectedUser(data)
             }
         })
        }
    }
    private fun showSelectedUser(data : ListUserItems){
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER,data.login)
        startActivity(intent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}