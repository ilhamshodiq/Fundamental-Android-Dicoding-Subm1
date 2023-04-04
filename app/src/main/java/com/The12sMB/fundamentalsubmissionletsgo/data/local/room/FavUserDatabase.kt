package com.The12sMB.fundamentalsubmissionletsgo.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser

@Database(entities = [FavoriteUser::class], version = 1)
abstract class FavUserDatabase : RoomDatabase() {
    abstract fun favUserDao(): FavUserDao

    companion object {
        @Volatile
        private var INSTANCE: FavUserDatabase? = null

        @JvmStatic
        fun getDatabase(context: android.content.Context): FavUserDatabase {
            if (INSTANCE == null) {
                synchronized(FavUserDatabase::class.java) {
                    INSTANCE = androidx.room.Room.databaseBuilder(
                        context.applicationContext,
                        FavUserDatabase::class.java, "favorite_user_database"
                    )
                        .build()
                }
            }
            return INSTANCE as FavUserDatabase
        }
    }
}