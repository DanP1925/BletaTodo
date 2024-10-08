package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import com.danp1925.todolist.domain.models.Task
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class GetTaskUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockTasksRepository: ITasksRepository

    private val sut by lazy {
        GetTaskUseCase(mockTasksRepository)
    }

    @Test
    fun `Verify GetTaskUseCase fetches task with specific id`() = runTest {
        //GIVEN
        val taskId = 5
        val expected =
            Task(id = 5, title = "Titulo", description = "Description", isCompleted = true)
        coEvery { mockTasksRepository.getTask(taskId) } returns expected

        //WHEN
        val actual = sut(taskId)

        //THEN
        assertEquals(expected, actual)

    }

}