package com.danp1925.todolist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danp1925.todolist.domain.models.Task

@Entity(tableName = "Task")
data class LocalTask(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean
) {
    companion object {
        fun fromDomain(task: Task) = LocalTask(
            title = task.title,
            description = task.description,
            isCompleted = task.isCompleted
        )
    }

    fun toDomain() = Task(
        id = uid!!,
        title = title,
        description = description,
        isCompleted = isCompleted
    )
}