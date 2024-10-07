package com.danp1925.todolist.data.di

import com.danp1925.todolist.data.TasksRepository
import com.danp1925.todolist.domain.ITasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TasksModule {
}

@InstallIn(SingletonComponent::class)
@Module
abstract class TasksBindingsModule {
    @Binds
    abstract fun provideTasksRepository(tasksRepository: TasksRepository): ITasksRepository

}