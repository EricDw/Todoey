package dev.ericd.todoey.viewmodels

import androidx.compose.ui.text.AnnotatedString
import dev.ericd.todoey.common.logs.TestLogger
import dev.ericd.todoey.core.tasks.TaskModel
import dev.ericd.todoey.data.repositories.fakes.FakeTaskRepository
import dev.ericd.todoey.ui.components.TaskState
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TheTasksViewModel {

    private lateinit var taskRepository: FakeTaskRepository

    private lateinit var viewModel: TasksViewModel

    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setUp() {

        dispatcher = StandardTestDispatcher()

        Dispatchers.setMain(dispatcher)

        taskRepository = FakeTaskRepository()

        val logger = TestLogger()

        viewModel = TasksViewModelLogger(
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
            TaskState(
                description = description
            )
        )

        // Act
        viewModel.addTask(
            description = description.text,
        )

        dispatcher.scheduler.advanceUntilIdle()

        val actual = viewModel.tasks.value

        println("Actual: $actual")

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun `Gets all Tasks`() {
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

        tasks.forEach(taskRepository::insert)

        val expected = listOf(
            TaskState(
                description = AnnotatedString("Buy Meat")
            ),
            TaskState(
                description = AnnotatedString("Buy Dairy")
            ),
            TaskState(
                description = AnnotatedString("Buy Spices")
            ),
        )

        // Act
        viewModel.getAllTasks()

        val actual = viewModel.tasks.value

        println("Actual: $actual")

        // Assert
        assertEquals(expected, actual)
    }

}
