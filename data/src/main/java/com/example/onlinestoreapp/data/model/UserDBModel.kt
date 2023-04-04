package com.example.onlinestoreapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onlinestoreapp.core.Constants.USERS

@Entity(tableName = USERS)
data class UserDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    var image: String?
)