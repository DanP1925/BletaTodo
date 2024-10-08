package com.danp1925.todolist.presentation.newtask

import com.danp1925.todolist.domain.models.Task
import com.danp1925.todolist.domain.usecases.AddNewTaskUseCase
import com.danp1925.todolist.presentation.MainDispatcherRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class NewTaskViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var mockAddNewTaskUseCase: AddNewTaskUseCase

    private val sut by lazy {
        NewTaskViewModel(mockAddNewTaskUseCase)
    }

    @Test
    fun `Verify updateTitle updates uiState`() {
        //GIVEN
        val testTitle = "Titulo"

        //WHEN
        sut.updateTitle(testTitle)

        //THEN
        assertEquals(testTitle, sut.uiState.value.taskTitle)
    }

    @Test
    fun `Verify updateDescription updates uiState`() {
        //GIVEN
        val testDescription = "Descripcion"

        //WHEN
        sut.updateDescription(testDescription)

        //THEN
        assertEquals(testDescription, sut.uiState.value.taskDescription)
    }

    @Test
    fun `Verify addNewTask calls the addNewTaskUseCase`() {
        //GIVEN
        val taskTitle = "Titulo"
        val taskDescription = "Descripcion"
        val expected =
            Task(id = null, title = taskTitle, description = taskDescription, isCompleted = false)
        coEvery { mockAddNewTaskUseCase(any()) } just Runs

        //WHEN
        sut.updateTitle(taskTitle)
        sut.updateDescription(taskDescription)
        sut.addNewTask()

        //THEN
        coVerify { mockAddNewTaskUseCase(expected) }
    }

}