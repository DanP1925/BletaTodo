package com.danp1925.todolist.data.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TasksDao {

    @Insert
    fun insertTask(task: LocalTask)

}