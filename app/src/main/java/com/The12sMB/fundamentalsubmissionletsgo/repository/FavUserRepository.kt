package com.The12sMB.fundamentalsubmissionletsgo.repository

import android.app.Application
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser
import com.The12sMB.fundamentalsubmissionletsgo.data.local.room.FavUserDao
import com.The12sMB.fundamentalsubmissionletsgo.data.local.room.FavUserDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavUserRepository(application: Application) {
    private val mFavUser : FavUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavUserDatabase.getDatabase(application)
        mFavUser = db.favUserDao()
    }

    fun insert(favoriteUser: FavoriteUser) {
        executorService.execute { mFavUser.insert(favoriteUser) }
    }

    fun delete(favoriteUser: FavoriteUser) {
        executorService.execute { mFavUser.delete(favoriteUser) }
    }

    fun isFav(userId: String) = mFavUser.isFavorite(userId)

    fun getAllFavUser() = mFavUser.getAllFavUser()

}