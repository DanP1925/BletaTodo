package com.danp1925.todolist.data.di

import android.content.Context
import androidx.room.Room
import com.danp1925.todolist.data.TasksRepository
import com.danp1925.todolist.data.local.TasksDao
import com.danp1925.todolist.data.local.TasksDb
import com.danp1925.todolist.domain.ITasksRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TasksModule {

    @Provides
    fun provideRoomDao(database: TasksDb): TasksDao {
        return database.tasksDao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): TasksDb {
        return Room.databaseBuilder(
            appContext,
            TasksDb::class.java,
            "tasks_database"
        ).fallbackToDestructiveMigration().build()
    }

}

@InstallIn(SingletonComponent::class)
@Module
abstract class TasksBindingsModule {
    @Binds
    abstract fun provideTasksRepository(tasksRepository: TasksRepository): ITasksRepository

}