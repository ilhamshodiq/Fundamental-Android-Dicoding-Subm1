package com.The12sMB.fundamentalsubmissionletsgo.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteUser(
    @PrimaryKey(autoGenerate = false)
    val userId: String,
    var username: String? = null, //login
    var avatarUrl: String? = null,
    var htmlUrl: String? = null,
) : Parcelable