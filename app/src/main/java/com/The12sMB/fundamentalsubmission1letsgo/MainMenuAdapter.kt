package com.The12sMB.fundamentalsubmission1letsgo

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.The12sMB.fundamentalsubmission1letsgo.databinding.ItemCardviewGithubuserBinding
import com.bumptech.glide.Glide

class MainMenuAdapter(private val listGithubUser: List<GithubUserResponse>) :
    RecyclerView.Adapter<MainMenuAdapter.ViewHolder>() {

    class ViewHolder(private  var binding: ItemCardviewGithubuserBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgGithubuserPhoto: ImageView = binding.githubuserProfile
        val tvUsername: TextView = binding.tvUsername
        val tvUrl: TextView = binding.tvHtmlurl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardviewGithubuserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = listGithubUser[position]
        holder.tvUsername.text = users.login
        holder.tvUrl.text = users.htmlUrl
        Glide.with(holder.itemView.context)
            .load(users.avatarUrl)
            .into(holder.imgGithubuserPhoto)

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_LOGIN, users.login)//intent data login
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return listGithubUser.size
    }

}