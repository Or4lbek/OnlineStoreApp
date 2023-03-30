package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.data.db.AuthorizationDao
import com.example.onlinestoreapp.data.mapper.AuthorizationMapper
import com.example.onlinestoreapp.domain.model.User
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

    override suspend fun createUser(user: User) {
        withContext(Dispatchers.IO) {
            authorizationDao.createUser(mapper.mapEntityToDBModel(user))
        }
    }

    override suspend fun loginUser(email: String, password: String): Int? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.loginUser(email, password))?.id
        }

    override suspend fun getUserByTokenId(id: Int): User? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.getUserById(id))
        }
}