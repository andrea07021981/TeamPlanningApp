package com.example.teamplanningapp.data.source.local

import com.example.teamplanningapp.data.source.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//TODO user firebase offline here
class UserLocalDataSource internal constructor(
    //private val userDatabaseDao: UserDatabaseDao,
    //private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {

    /*override suspend fun getUser(email: String, password: String): Result<UserDomain> = withContext(ioDispatcher){
        val user = userDatabaseDao.getUser(email, password)
        if (user != null) {
            return@withContext Result.Success(user.asDomainModel())
        } else {
            return@withContext Result.Error("User not found")
        }

    }

    override suspend fun saveUser(user: UserDomain) = withContext(ioDispatcher){
        userDatabaseDao.insert(user.asDatabaseModel())
    }*/
}