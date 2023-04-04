package com.The12sMB.fundamentalsubmissionletsgo.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser
import com.The12sMB.fundamentalsubmissionletsgo.databinding.ItemCardviewGithubuserBinding
import com.The12sMB.fundamentalsubmissionletsgo.ui.detail.DetailActivity
import com.bumptech.glide.Glide

class FavoriteUserAdapter : RecyclerView.Adapter<FavoriteUserAdapter.FavoriteUserViewHolder>() {

    private val favoriteUserList = ArrayList<FavoriteUser>()

    fun setFavorites(favoriteUser: List<FavoriteUser>) {
        val diffCallback = FavoriteDiffCallback(this.favoriteUserList, favoriteUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.favoriteUserList.clear()
        this.favoriteUserList.addAll(favoriteUser)
        diffResult.dispatchUpdatesTo(this)
    }

    class FavoriteUserViewHolder(private val binding: ItemCardviewGithubuserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteUser: FavoriteUser) {
            with(binding) {
                tvUsername.text = favoriteUser.username
                tvHtmlurl.text = favoriteUser.htmlUrl
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_LOGIN, favoriteUser.username)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(favoriteUser.avatarUrl)
                .into(binding.githubuserProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        val itemRowUserBinding = ItemCardviewGithubuserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteUserViewHolder(itemRowUserBinding)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        val favoriteUser = favoriteUserList[position]
        holder.bind(favoriteUser)
    }

    override fun getItemCount(): Int {
        return favoriteUserList.size
    }
}
