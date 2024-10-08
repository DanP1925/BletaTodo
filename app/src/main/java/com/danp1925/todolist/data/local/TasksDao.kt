package com.danp1925.todolist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM Task")
    fun getTasks(): Flow<List<LocalTask>>

    @Query("SELECT * FROM Task WHERE :taskId = uid")
    suspend fun getTask(taskId: Int): LocalTask?

    @Insert
    suspend fun insertTask(task: LocalTask)

    @Update(entity = LocalTask::class)
    suspend fun updateCompletion(partialTask: PartialLocalTask)

    @Delete
    suspend fun deleteTask(task: LocalTask)

}