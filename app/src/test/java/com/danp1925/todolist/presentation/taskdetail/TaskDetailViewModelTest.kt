package com.danp1925.todolist.presentation.taskdetail

import androidx.lifecycle.SavedStateHandle
import com.danp1925.todolist.domain.models.Task
import com.danp1925.todolist.domain.usecases.DeleteTaskUseCase
import com.danp1925.todolist.domain.usecases.GetTaskUseCase
import com.danp1925.todolist.domain.usecases.UpdateTaskCompletionUseCase
import com.danp1925.todolist.presentation.MainDispatcherRule
import com.danp1925.todolist.ui.navigation.NavRoutes
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.runs
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
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

    @MockK
    lateinit var mockDeleteTaskUseCase: DeleteTaskUseCase

    private val taskId = 5

    private val sut by lazy {
        TaskDetailViewModel(
            SavedStateHandle(mapOf(NavRoutes.TaskDetailArgs.TaskId to taskId)),
            mockGetTaskUseCase,
            mockUpdateTaskCompletionUseCase,
            mockDeleteTaskUseCase
        )
    }

    @Before
    fun setUp() {
        val testTask =
            Task(id = taskId, title = "Titulo", description = "Descripcion", isCompleted = true)
        coEvery { mockGetTaskUseCase(taskId) } returns testTask
    }

    @Test
    fun `Verify that the task is loaded when initialized`() = runTest {
        //THEN
        with(sut.uiState.value) {
            assertEquals("Titulo", title)
            assertEquals("Descripcion", description)
            assertEquals(true, isCompleted)
        }
        coVerify { mockGetTaskUseCase(taskId) }
    }

    @Test
    fun `Verify that loadTask handle when an exception is thrown`() = runTest {
        //GIVEN
        val exceptionMessage = "Test exception"
        coEvery { mockGetTaskUseCase(taskId) } throws Exception(exceptionMessage)
        var event: TaskDetailEvents? = null

        //WHEN
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            event = sut.eventFlow.first()
        }

        //THEN
        with(sut.uiState.value) {
            assertEquals(false, isLoading)
        }
        assertEquals(TaskDetailEvents.OnExceptionThrown(exceptionMessage), event)
    }

    @Test
    fun `Verify that updateCompletionStatus updates the uiState`() = runTest {
        //GIVEN
        coEvery { mockUpdateTaskCompletionUseCase(taskId, true) } returns false

        //WHEN
        sut.updateCompletionStatus()

        //THEN
        assertEquals(false, sut.uiState.value.isCompleted)
    }

    @Test
    fun `Verify that deleteTask calls the deleteTaskUseCase and sends event`() = runTest {
        //GIVEN
        coEvery { mockDeleteTaskUseCase(any()) } just runs
        var event: TaskDetailEvents? = null

        //WHEN
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            event = sut.eventFlow.first()
        }
        sut.deleteTask()

        //THEN
        coVerify { mockGetTaskUseCase(any()) }
        assertEquals(TaskDetailEvents.OnDeleteCompleted, event)
    }

    @Test
    fun `Verify showDeleteDialog updates uiState`() {
        //WHEN
        sut.showDeleteDialog()

        //THEN
        assertEquals(true, sut.uiState.value.showAlertDialog)
    }

    @Test
    fun `Verify hideDeleteDialog updates uiState`() {
        //WHEN
        sut.hideDeleteDialog()

        //THEN
        assertEquals(false, sut.uiState.value.showAlertDialog)
    }

}