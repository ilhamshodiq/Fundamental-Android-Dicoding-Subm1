package com.The12sMB.fundamentalsubmission1letsgo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.The12sMB.fundamentalsubmission1letsgo.databinding.ItemCardviewGithubuserBinding
import com.bumptech.glide.Glide

class MainMenuAdapter(private val listGithubUser: List<GithubUserResponse>) :
    RecyclerView.Adapter<MainMenuAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgGithubuserPhoto: ImageView = view.findViewById(R.id.githubuser_profile)
        val tvUsername: TextView = view.findViewById(R.id.tv_username)
        val tvNama: TextView = view.findViewById(R.id.tv_nama)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cardview_githubuser, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = listGithubUser[position]
        holder.tvUsername.text = users.login
        holder.tvNama.text = users.htmlUrl
//        Glide.with(holder.itemView.context)
//            .load(users.avatarUrl)
//            .circleCrop()
//            .into(holder.imgPhoto)
    }

    override fun getItemCount(): Int {
        return listGithubUser.size
    }

}