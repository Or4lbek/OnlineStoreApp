package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.data.db.AuthorizationDao
import com.example.onlinestoreapp.data.mapper.AuthorizationMapper
import com.example.onlinestoreapp.domain.model.UserAuth
import com.example.onlinestoreapp.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthorizationRepositoryImpl(
    private val authorizationDao: AuthorizationDao
) : AuthorizationRepository {

    private val mapper = AuthorizationMapper()

    override suspend fun checkUser(email: String): Boolean =
        withContext(Dispatchers.IO) {
            authorizationDao.checkUserByEmail(email)
        }

    override suspend fun createUser(userAuth: UserAuth) {
        withContext(Dispatchers.IO) {
            authorizationDao.createUser(mapper.mapEntityToDBModel(userAuth))
        }
    }

    override suspend fun loginUser(email: String, password: String): Int? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.loginUser(email, password))?.id
        }

    override suspend fun getUserByTokenId(id: Int): UserAuth? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.getUserById(id))
        }
}