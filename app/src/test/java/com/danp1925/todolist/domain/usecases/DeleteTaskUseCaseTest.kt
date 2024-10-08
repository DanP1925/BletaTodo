package com.danp1925.todolist.domain.usecases

import com.danp1925.todolist.domain.ITasksRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class DeleteTaskUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockTasksRepository: ITasksRepository

    private val sut by lazy {
        DeleteTaskUseCase(mockTasksRepository)
    }

    @Test
    fun `Verify DeleteTaskUseCase calls delete from repository`() = runTest {
        //GIVEN
        val taskId = 5
        coEvery { mockTasksRepository.deleteTask(taskId) } just Runs

        //WHEN
        sut(taskId)

        //THEN
        coVerify { mockTasksRepository.deleteTask(taskId) }
    }

}