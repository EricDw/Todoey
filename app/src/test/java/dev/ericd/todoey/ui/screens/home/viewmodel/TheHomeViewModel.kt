package dev.ericd.todoey.ui.screens.home.viewmodel

import androidx.compose.ui.text.AnnotatedString
import dev.ericd.todoey.common.logs.TestLogger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.core.tasks.TaskModel
import dev.ericd.todoey.core.tasks.fakes.FakeTask
import dev.ericd.todoey.data.repositories.fakes.FakeTaskRepository
import dev.ericd.todoey.ui.components.TaskState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TheHomeViewModel {

    private lateinit var taskRepository: Task.Repository

    private lateinit var viewModel: HomeViewModel

    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setUp() {

        dispatcher = StandardTestDispatcher()

        Dispatchers.setMain(dispatcher)

        taskRepository = FakeTaskRepository()

        val logger = TestLogger()

        viewModel = HomeViewModelLogger(
            repository = taskRepository,
            logger = logger
        )

    }

    @After
    fun tearDown() {
        println("\n")
        Dispatchers.resetMain()
    }

    @Test
    fun `Can Add a Task`() {
        // Arrange
        val description = AnnotatedString("Buy Meat")

        val expected = listOf(
            TaskState {

                this.details = description

            }
        )

        viewModel.loadAllTasks()

        // Act
        viewModel.addTask(
            details = description.text,
        )

        dispatcher.scheduler.advanceUntilIdle()

        val actual = viewModel.tasks.value

        println("Actual: $actual")

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun `Can load all Tasks`() = runTest {
        // Arrange

        val tasks = listOf(
            TaskModel(
                "Buy Meat"
            ),
            TaskModel(
                "Buy Dairy"
            ),
            TaskModel(
                "Buy Spices"
            ),
        )

        tasks.forEach {
            taskRepository.insert(it)
        }

        val expected = listOf(
            TaskState {
                title = AnnotatedString("Buy Meat")
            },
            TaskState {
                title = AnnotatedString("Buy Dairy")
            },
            TaskState {
                title = AnnotatedString("Buy Spices")
            },
        )

        // Act
        viewModel.loadAllTasks()

        dispatcher.scheduler.advanceUntilIdle()

        val actual = viewModel.tasks.value

        println("Actual: $actual")

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun `Can delete a task`() = runTest {
        // Arrange
        val expected = emptyList<TaskState>()

        taskRepository.insert(
            FakeTask("")
        )

        dispatcher.scheduler.advanceUntilIdle()

        viewModel.loadAllTasks()

        dispatcher.scheduler.advanceUntilIdle()

        assertTrue(
            "The task list is expected to be empty",
            viewModel.tasks.value.isNotEmpty()
        )

        assertTrue(
            "Deletion is expected to be enabled",
            viewModel.tasks.value.first().deleteButtonState.isEnabled
        )

        // Act
        viewModel.tasks.value.first().deleteButtonState.clickHandler()

        dispatcher.scheduler.advanceUntilIdle()

        val actual = viewModel.tasks.value

        // Assert
        assertEquals(expected, actual)
    }

}
