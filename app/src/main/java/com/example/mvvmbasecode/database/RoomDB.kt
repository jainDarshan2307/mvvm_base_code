package com.example.mvvmbasecode.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmbasecode.database.userdata.UserDataDao
import com.example.mvvmbasecode.database.userdata.UserDataEntity

@Database(
    exportSchema = false,
    entities = [UserDataEntity::class],
    version = 1
)
abstract class RoomDB : RoomDatabase() {
    abstract fun UserDataDao(): UserDataDao
}