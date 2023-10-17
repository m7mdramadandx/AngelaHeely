package com.example.angelaheely.room_db.db

import androidx.room.*
import com.example.angelaheely.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getCurrentUser(): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}