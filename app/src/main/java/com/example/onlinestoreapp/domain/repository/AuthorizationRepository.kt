package com.example.onlinestoreapp.domain.repository

import com.example.onlinestoreapp.domain.model.UserAuth

interface AuthorizationRepository {

    suspend fun checkUser(email: String): Boolean

    suspend fun createUser(userAuth: UserAuth)

    suspend fun loginUser(email: String, password: String): Int?

    suspend fun getUserByTokenId(id: Int): UserAuth?
}