package com.example.onlinestoreapp.domain.repository

import com.example.onlinestoreapp.domain.model.UserAuth

interface OnlineStoreRepository {

    suspend fun checkUser(email: String): Boolean

    suspend fun createUser(userAuth: UserAuth)

    suspend fun loginUser(email: String, password: String): UserAuth?

}