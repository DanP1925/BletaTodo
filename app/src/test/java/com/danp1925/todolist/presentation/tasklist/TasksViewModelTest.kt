package com.danp1925.todolist.presentation.tasklist

import androidx.lifecycle.viewmodel.compose.viewModel
import com.danp1925.todolist.domain.models.Task
import com.danp1925.todolist.domain.usecases.GetTasksUseCase
import com.danp1925.todolist.presentation.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class TasksViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var mockGetTasksUseCase: GetTasksUseCase

    private val sut by lazy {
        TasksViewModel(mockGetTasksUseCase)
    }

    @Test
    fun `Verify tasks are fetched on initialization`() = runTest {
        //GIVEN
        val testTasks = listOf(
            Task(3, "Titulo1", "Descripcion1", false),
            Task(4, "Titulo2", "Descripcion2", true),
            Task(5, "Titulo3", "Descripcion3", false),
        )
        coEvery { mockGetTasksUseCase() } returns flow {
            emit(testTasks)
        }

        //WHEN
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.collect()
        }

        //THEN
        val actualTasks = sut.uiState.value.tasks
        assertEquals(3, actualTasks.size)
        actualTasks.forEachIndexed{ index, uiTask ->
            assertEquals(testTasks[index].id, uiTask.id)
            assertEquals(testTasks[index].title, uiTask.title)
            assertEquals(testTasks[index].isCompleted, uiTask.isCompleted)
        }
    }

}