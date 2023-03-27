package com.example.onlinestoreapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlinestoreapp.data.model.UserAuthDBModel

@Dao
interface AuthorizationDao {
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")// EXIST
    suspend fun checkUserByEmail(email: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(userAuthDBModel: UserAuthDBModel)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): UserAuthDBModel?

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): UserAuthDBModel
}