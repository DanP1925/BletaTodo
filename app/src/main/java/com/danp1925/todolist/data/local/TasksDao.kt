package com.danp1925.todolist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM Task")
    fun getTasks(): Flow<List<LocalTask>>

    @Query("SELECT * FROM Task WHERE :taskId = uid")
    fun getTask(taskId: Int): LocalTask

    @Insert
    suspend fun insertTask(task: LocalTask)

}