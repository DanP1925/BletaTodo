package com.danp1925.todolist.data

import com.danp1925.todolist.data.local.LocalTask
import com.danp1925.todolist.data.local.PartialLocalTask
import com.danp1925.todolist.data.local.TasksDao
import com.danp1925.todolist.domain.models.Task
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TasksRepositoryTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @MockK
    lateinit var mockTasksDao: TasksDao

    private val sut by lazy {
        TasksRepository(tasksDao = mockTasksDao, dispatcher)
    }

    @Test
    fun `Verify getTasks fetches all tasks from local database`() = scope.runTest {
        //GIVEN
        val testTasks = listOf(
            LocalTask(3, "Titulo1", "Descripcion1", false),
            LocalTask(4, "Titulo2", "Descripcion2", true),
            LocalTask(5, "Titulo3", "Descripcion3", false),
        )
        val expectedTasks = listOf(
            Task(3, "Titulo1", "Descripcion1", false),
            Task(4, "Titulo2", "Descripcion2", true),
            Task(5, "Titulo3", "Descripcion3", false),
        )
        coEvery { mockTasksDao.getTasks() } returns flow {
            emit(testTasks)
        }

        //WHEN
        val actualTasks = sut.getTasks().first()

        //THEN
        assertEquals(expectedTasks.size, actualTasks.size)
        actualTasks.forEachIndexed { index, task ->
            assertEquals(expectedTasks[index], task)
        }

    }

    @Test
    fun `Verify getTask fetches task from local database`() = scope.runTest {
        //GIVEN
        val taskId = 5
        val expected = Task(5, "Titulo", "Descripcion", true)
        coEvery { mockTasksDao.getTask(taskId) } returns LocalTask(
            5,
            "Titulo",
            "Descripcion",
            true
        )

        //WHEN
        val actual = sut.getTask(taskId)

        //THEN
        assertEquals(expected, actual)
    }

    @Test
    fun `Verify addNewTask insert newTask into database`() = scope.runTest {
        //GIVEN
        val testTask = Task(null, "Titulo", "Descripcion", false)
        val expected = LocalTask(null, "Titulo", "Descripcion", false)
        coEvery { mockTasksDao.insertTask(any()) } just Runs

        //WHEN
        sut.addNewTask(testTask)

        //THEN
        coVerify {
            mockTasksDao.insertTask(expected)
        }
    }

    @Test
    fun `Verify updateTaskCompletion updates isCompleted value from database`() = scope.runTest {
        //GIVEN
        val taskId = 5
        val newCompletionValue = true
        val expected = PartialLocalTask(5, true)
        coEvery { mockTasksDao.updateCompletion(any()) } just Runs

        //WHEN
        sut.updateTaskCompletion(taskId, newCompletionValue)

        //THEN
        coVerify {
            mockTasksDao.updateCompletion(expected)
        }
    }

}