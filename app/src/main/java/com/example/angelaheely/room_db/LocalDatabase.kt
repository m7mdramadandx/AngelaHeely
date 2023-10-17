package com.example.angelaheely.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.angelaheely.domain.model.User
import com.example.angelaheely.room_db.db.UserDao

@Database(
    version = 1,
    entities = [User::class],
    exportSchema = false,
)

abstract class LocalDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}