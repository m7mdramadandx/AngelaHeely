package com.example.angelaheely.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/** IMPORTANT NOTE
 * the best practice to make one class for database layer
 * and another one for presentation layer and mapping between them
 */

@Entity(tableName = "user")
@Parcelize
data class User(
    @PrimaryKey val username: String,
    val password: String
) : Parcelable
