package com.example.onlinestoreapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onlinestoreapp.utils.Constants.USERS

@Entity(tableName = USERS)
data class UserAuthDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val password: String
) {
    override fun toString(): String {
        return "UserAuthDBModel(id=$id, name='$name', email='$email', password='$password')"
    }
}