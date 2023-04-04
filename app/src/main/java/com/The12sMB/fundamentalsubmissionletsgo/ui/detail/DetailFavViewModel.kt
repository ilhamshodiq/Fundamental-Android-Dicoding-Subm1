package com.The12sMB.fundamentalsubmissionletsgo.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser
import com.The12sMB.fundamentalsubmissionletsgo.repository.FavUserRepository

class DetailFavViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavUserRepository =
        FavUserRepository(application)

    fun isFav(userId: String): LiveData<Boolean> =
        mFavoriteUserRepository.isFav(userId)

    fun insert(favuser: FavoriteUser) {
        mFavoriteUserRepository.insert(favuser)
    }

    fun delete(favuser: FavoriteUser) {
        mFavoriteUserRepository.delete(favuser)
    }
}