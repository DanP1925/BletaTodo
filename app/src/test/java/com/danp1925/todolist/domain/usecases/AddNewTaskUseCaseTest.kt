package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AddNewTaskUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockTasksRepository: ITasksRepository

    private val sut by lazy {
        AddNewTaskUseCase(mockTasksRepository)
    }

    @Test
    fun `Verify AddNewTaskUseCase calls the repository`() = runTest {
        //GIVEN
        val testTask =
            Task(id = 5, title = "Titulo", description = "Descripcion", isCompleted = true)
        coEvery { mockTasksRepository.addNewTask(any()) } just Runs

        //WHEN
        sut(testTask)

        //THEN
        coVerify { mockTasksRepository.addNewTask(testTask) }
    }

}