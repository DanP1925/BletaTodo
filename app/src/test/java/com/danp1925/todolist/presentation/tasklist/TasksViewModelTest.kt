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
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
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

    private val testTasks = listOf(
        Task(3, "Titulo 2", "Descripcion1", false),
        Task(4, "Titulo 3", "Descripcion2", true),
        Task(5, "Titulo 1", "Descripcion3", false),
    )

    @Before
    fun setup() {
        coEvery { mockGetTasksUseCase() } returns flow {
            emit(testTasks)
        }
    }

    private fun TestScope.startCollectingUiState() {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.collect()
        }
    }

    @Test
    fun `Verify tasks are fetched on initialization`() = runTest {
        //WHEN
        startCollectingUiState()

        //THEN
        val actualTasks = sut.uiState.value.tasks
        assertEquals(3, actualTasks.size)
        actualTasks.forEachIndexed { index, uiTask ->
            assertEquals(testTasks[index].id, uiTask.id)
            assertEquals(testTasks[index].title, uiTask.title)
            assertEquals(testTasks[index].isCompleted, uiTask.isCompleted)
        }
    }

    @Test
    fun `Verify showMenu updates shouldShowMenu flow`() = runTest {
        //WHEN
        startCollectingUiState()
        sut.showMenu()

        //THEN
        assertEquals(true, sut.uiState.value.shouldShowMenu)
    }

    @Test
    fun `Verify hideMenu updates shouldShowMenu flow`() = runTest {
        //WHEN
        startCollectingUiState()
        sut.hideMenu()

        //THEN
        assertEquals(false, sut.uiState.value.shouldShowMenu)
    }

    @Test
    fun `Verify sortTasksById updates uiState`() = runTest {
        //WHEN
        startCollectingUiState()
        sut.sortTasksById()

        //THEN
        assertEquals(false, sut.uiState.value.shouldShowMenu)
        assertEquals(3, sut.uiState.value.tasks.first().id)
    }

    @Test
    fun `Verify sortTasksAlphabetically updates uiState`() = runTest {
        //WHEN
        startCollectingUiState()
        sut.sortTasksAlphabetically()

        //THEN
        assertEquals(false, sut.uiState.value.shouldShowMenu)
        assertEquals("Titulo 1", sut.uiState.value.tasks.first().title)
    }

}