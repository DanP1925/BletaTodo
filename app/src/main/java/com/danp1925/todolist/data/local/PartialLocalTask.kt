package com.danp1925.todolist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class PartialLocalTask(
    val uid: Int,
    @ColumnInfo("is_completed") val isCompleted: Boolean
)