package com.danp1925.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalTask::class],
    version = 1
)
abstract class TasksDb : RoomDatabase() {
    abstract val tasksDao: TasksDao
}