package com.example.taskifyy

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: com.example.taskifyy.Entity)

    @Update
    suspend fun updateTask(entity: com.example.taskifyy.Entity)

    @Delete
    suspend fun deleteTask(entity: com.example.taskifyy.Entity)

    @Query("DELETE FROM To_Do")
    suspend fun deleteAll()

    @Query("SELECT * FROM To_Do")
    suspend fun getTasks(): List<TaskInfo>


}