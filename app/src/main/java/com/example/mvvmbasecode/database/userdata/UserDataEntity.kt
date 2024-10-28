package com.example.mvvmbasecode.database.userdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
class UserDataEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "maidenName") val maidenName: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "image") val image: String?,
)