package com.example.angelaheely.data.source.local

import com.example.angelaheely.domain.model.User
import com.example.angelaheely.room_db.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val userDao: UserDao
) : LocalDataSource {

    override fun setUser(user: User) = userDao.addUser(user)

    override fun getCurrentUser(): Flow<User?> = channelFlow {
        userDao.getCurrentUser()
            .catch {
                it.printStackTrace()
                channel.close(null)
            }
            .collectLatest {
                channel.send(it)
            }

    }.flowOn(Dispatchers.IO)

    override fun deleteAll() = userDao.deleteAll()


}