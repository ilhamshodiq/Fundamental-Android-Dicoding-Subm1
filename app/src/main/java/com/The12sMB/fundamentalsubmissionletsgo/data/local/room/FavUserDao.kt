package com.The12sMB.fundamentalsubmissionletsgo.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(FavUser: FavoriteUser)

    @Update
    fun update(FavUser: FavoriteUser)

    @Delete
    fun delete(FavUser: FavoriteUser)

    //untuk menandai user favorite
    @Query("SELECT EXISTS(SELECT * FROM FavoriteUser WHERE userId = :userId)")
    fun isFavorite(userId: String): LiveData<Boolean>

    @Query("SELECT * from FavoriteUser")
    fun getAllFavUser(): LiveData<List<FavoriteUser>>

}