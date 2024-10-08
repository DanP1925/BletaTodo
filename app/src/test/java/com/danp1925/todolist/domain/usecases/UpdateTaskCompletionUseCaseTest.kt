package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class UpdateTaskCompletionUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockTasksRepository: ITasksRepository

    private val sut by lazy {
        UpdateTaskCompletionUseCase(mockTasksRepository)
    }

    @Test
    fun `Verify UpdateTaskCompletionUseCase updates the database and returns new status`() =
        runTest {
            //GIVEN
            val taskId = 5
            val isCompleted = false
            val expected = true
            coEvery { mockTasksRepository.updateTaskCompletion(taskId, expected) } just runs

            //WHEN
            val actual = sut(taskId, isCompleted)

            //THEN
            coVerify { mockTasksRepository.updateTaskCompletion(taskId, expected) }
            assertEquals(expected, actual)

        }

}