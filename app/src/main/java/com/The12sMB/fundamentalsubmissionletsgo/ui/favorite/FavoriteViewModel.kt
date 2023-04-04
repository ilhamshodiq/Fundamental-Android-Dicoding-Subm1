package com.The12sMB.fundamentalsubmissionletsgo.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser
import com.The12sMB.fundamentalsubmissionletsgo.repository.FavUserRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavUserRepository: FavUserRepository = FavUserRepository(application)
    fun getAllFavUser(): LiveData<List<FavoriteUser>> = mFavUserRepository.getAllFavUser()
}