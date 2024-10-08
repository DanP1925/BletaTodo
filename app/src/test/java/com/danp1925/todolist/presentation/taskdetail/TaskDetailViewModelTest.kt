package com.danp1925.todolist.presentation.taskdetail

import androidx.lifecycle.SavedStateHandle
import com.danp1925.todolist.domain.models.Task
import com.danp1925.todolist.domain.usecases.GetTaskUseCase
import com.danp1925.todolist.domain.usecases.UpdateTaskCompletionUseCase
import com.danp1925.todolist.presentation.MainDispatcherRule
import com.danp1925.todolist.ui.navigation.NavRoutes
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TaskDetailViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var mockGetTaskUseCase: GetTaskUseCase

    @MockK
    lateinit var mockUpdateTaskCompletionUseCase: UpdateTaskCompletionUseCase

    private val taskId = 5

    private val sut by lazy {
        TaskDetailViewModel(
            SavedStateHandle(mapOf(NavRoutes.TaskDetailArgs.TaskId to taskId)),
            mockGetTaskUseCase,
            mockUpdateTaskCompletionUseCase
        )
    }

    @Before
    fun setUp() {
        val testTask =
            Task(id = taskId, title = "Titulo", description = "Descripcion", isCompleted = true)
        coEvery { mockGetTaskUseCase(taskId) } returns testTask
    }

    @Test
    fun `Verify that the task is loaded when initialized`() {
        //THEN
        with(sut.uiState.value) {
            assertEquals("Titulo", title)
            assertEquals("Descripcion", description)
            assertEquals(true, isCompleted)
        }
        coVerify { mockGetTaskUseCase(taskId) }
    }

    @Test
    fun `Verify that updateCompletionStatus updates the uiState`() {
        //GIVEN
        coEvery { mockUpdateTaskCompletionUseCase(taskId, false) } returns true

        //WHEN
        sut.updateCompletionStatus()

        //THEN
        assertEquals(true, sut.uiState.value.isCompleted)
    }

}