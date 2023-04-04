package com.The12sMB.fundamentalsubmissionletsgo.ui.favorite

import androidx.recyclerview.widget.DiffUtil
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser

class FavoriteDiffCallback(
    private val mOldFavoriteUser: List<FavoriteUser>,
    private val mNewFavoriteUser: List<FavoriteUser>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteUser.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteUser.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteUser[oldItemPosition].userId == mNewFavoriteUser[newItemPosition].userId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavoriteUser = mOldFavoriteUser[oldItemPosition]
        val newFavoriteUser = mNewFavoriteUser[newItemPosition]
        return oldFavoriteUser.username == newFavoriteUser.username && oldFavoriteUser.avatarUrl == newFavoriteUser.avatarUrl
    }

}

