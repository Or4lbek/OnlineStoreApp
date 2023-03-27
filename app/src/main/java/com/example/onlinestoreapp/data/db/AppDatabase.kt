package com.example.onlinestoreapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlinestoreapp.data.model.UserAuthDBModel

@Database(entities = [UserAuthDBModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun onlineStoreDao(): AuthorizationDao
}