package com.example.fundamental_2.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.fundamental_2.databinding.RowItemsGithubBinding
import com.example.fundamental_2.response.ListUserItems

class MainAdapter(private val listGithub : List<ListUserItems>):RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback  : OnItemClickCallback

    inner class ViewHolder( val binding: RowItemsGithubBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(listUser : ListUserItems){
            binding.apply {
                Glide.with(itemView)
                    .load(listUser.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .override(200,200)
                    .into(imageView)
                loginRows.text = listUser.login
                urlRows.text = listUser.url
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemsGithubBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGithub[position])
        holder.binding.root.setOnClickListener{
            onItemClickCallback.onItemClicked(listGithub[position])
        }
    }

    override fun getItemCount(): Int {
        return listGithub.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: ListUserItems)
    }
}