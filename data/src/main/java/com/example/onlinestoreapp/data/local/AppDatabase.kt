package com.example.onlinestoreapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlinestoreapp.data.model.UserDBModel

@Database(entities = [UserDBModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun onlineStoreDao(): AuthorizationDao
}