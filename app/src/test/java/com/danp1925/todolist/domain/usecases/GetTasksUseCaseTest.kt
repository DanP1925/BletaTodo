package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class GetTasksUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockTasksRepository: ITasksRepository

    private val sut by lazy {
        GetTasksUseCase(mockTasksRepository)
    }

    @Test
    fun `Verify GetTasksUseCase fetches all tasks from repository`() = runTest {
        //GIVEN
        val testTasks = listOf(
            Task(3, "Titulo1", "Descripcion1", false),
            Task(4, "Titulo2", "Descripcion2", true),
            Task(5, "Titulo3", "Descripcion3", false),
        )
        coEvery { mockTasksRepository.getTasks() } returns flow {
            emit(testTasks)
        }

        //WHEN
        val actualTasks = sut().first()

        //THEN
        assertEquals(3, actualTasks.size)
        actualTasks.forEachIndexed { index, actualTask ->
            assertEquals(testTasks[index], actualTask)
        }
    }

}