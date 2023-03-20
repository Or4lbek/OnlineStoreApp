package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.data.db.OnlineStoreDao
import com.example.onlinestoreapp.data.mapper.OnlineStoreMapper
import com.example.onlinestoreapp.domain.model.UserAuth
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnlineStoreRepositoryImpl(
    private val onlineStoreDao: OnlineStoreDao
) : OnlineStoreRepository {

    private val mapper = OnlineStoreMapper()

    override suspend fun checkUser(email: String): Boolean =
        withContext(Dispatchers.IO) {
            onlineStoreDao.checkUserByEmail(email)
        }

    override suspend fun createUser(userAuth: UserAuth) {
        withContext(Dispatchers.IO) {
            onlineStoreDao.createUser(mapper.mapEntityToDBModel(userAuth))
        }
    }

    override suspend fun loginUser(email: String, password: String): UserAuth? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(onlineStoreDao.loginUser(email, password))
        }

}