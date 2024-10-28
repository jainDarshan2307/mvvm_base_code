package com.example.mvvmbasecode.database.userdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDataDao {

    @Query("SELECT * FROM user_data")
    fun getAllRows(): List<UserDataEntity>

    @Query("SELECT * FROM user_data WHERE id IN (:id)")
    fun getRowById(id: Int): UserDataEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(row: UserDataEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMultiple(vararg rows: UserDataEntity): Array<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMultiple(rows: List<UserDataEntity>): Array<Long>

    @Update
    fun update(row: UserDataEntity)

    @Update
    fun updateMultiple(rows: List<UserDataEntity>)

    @Update
    fun updateMultiple(vararg rows: UserDataEntity)

    @Delete
    fun delete(row: UserDataEntity)

    @Delete
    fun deleteMultiple(rows: List<UserDataEntity>)

    @Delete
    fun deleteMultiple(vararg rows: UserDataEntity)

    @Query("DELETE FROM user_data")
    fun deleteAll()
}