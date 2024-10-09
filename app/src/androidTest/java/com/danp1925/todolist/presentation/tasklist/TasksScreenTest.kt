package com.danp1925.todolist.presentation.tasklist

import android.content.Context
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.danp1925.todolist.R
import com.danp1925.todolist.domain.models.Task
import com.danp1925.todolist.domain.usecases.GetTasksUseCase
import com.danp1925.todolist.ui.theme.TODOListTheme
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test

class TasksScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockGetTasksUseCase: GetTasksUseCase

    private val testTasks = listOf(
        Task(3, "Titulo 2", "Descripcion1", false),
        Task(4, "Titulo 3", "Descripcion2", true),
        Task(5, "Titulo 1", "Descripcion3", false),
    )

    @Test
    fun initialState_isRendered() {
        //GIVEN
        coEvery { mockGetTasksUseCase() } returns flow {
            emit(testTasks)
        }

        //WHEN
        composeTestRule.setContent {
            TODOListTheme {
                TasksScreens(TasksViewModel(mockGetTasksUseCase), {}, {})
            }
        }

        //THEN
        composeTestRule.onNodeWithText("Titulo 1").isDisplayed()
        composeTestRule.onNodeWithText("Titulo 3").isDisplayed()
        composeTestRule.onNodeWithText("Titulo 2").isDisplayed()
    }

    @Test
    fun initialState_emptyMessageDisplayed() {
        //GIVEN
        coEvery { mockGetTasksUseCase() } returns flow {
            emit(emptyList())
        }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedMessage = context.resources.getString(R.string.tasks_screen_empty_list)

        //WHEN
        composeTestRule.setContent {
            TODOListTheme {
                TasksScreens(TasksViewModel(mockGetTasksUseCase), {}, {})
            }
        }

        //THEN
        composeTestRule
            .onNodeWithText(expectedMessage)
            .isDisplayed()
    }

    @Test
    fun clickOnSortAlphabeticallyButton_changesOrder(){
        //GIVEN
        coEvery { mockGetTasksUseCase() } returns flow {
            emit(testTasks)
        }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val menuDescription = context.resources.getString(R.string.tasks_screen_more_options)
        val sortAlphabeticallyText = context.resources.getString(R.string.tasks_screen_menu_alphabetically_order)

        //WHEN
        composeTestRule.setContent {
            TODOListTheme {
                TasksScreens(TasksViewModel(mockGetTasksUseCase), {}, {})
            }
        }
        composeTestRule.onNodeWithContentDescription(menuDescription).performClick()
        composeTestRule.onNodeWithText(sortAlphabeticallyText).performClick()

        //THEN
        composeTestRule.onNodeWithTag("TaskList").onChildAt(0).assert(hasText("Titulo 1"))

    }


}