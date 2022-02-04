package dev.ericd.todoey.viewmodels

import androidx.compose.ui.text.AnnotatedString
import dev.ericd.todoey.common.logs.TestLogger
import dev.ericd.todoey.core.tasks.TaskModel
import dev.ericd.todoey.core.tasks.fakes.FakeTask
import dev.ericd.todoey.ui.components.TaskState
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger
import dev.ericd.todoey.usecases.AddTaskUseCase
import dev.ericd.todoey.usecases.GetAllTasksUseCase
import dev.ericd.todoey.usecases.fakes.FakeAddTaskUseCase
import dev.ericd.todoey.usecases.fakes.FakeGetAllTasksUseCase
import dev.ericd.todoey.usecases.logs.GetAllTasksUseCaseLogger
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

    private lateinit var viewModel: TasksViewModel

    private lateinit var getAllTasksUseCase: FakeGetAllTasksUseCase

    private lateinit var addTaskUseCase: FakeAddTaskUseCase

    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setUp() {

        dispatcher = StandardTestDispatcher()

        Dispatchers.setMain(dispatcher)

        getAllTasksUseCase = FakeGetAllTasksUseCase()

        addTaskUseCase = FakeAddTaskUseCase()

        val logger = TestLogger()

        val caseLogger = GetAllTasksUseCaseLogger(
            delegate = getAllTasksUseCase,
            logger = logger
        )

        viewModel = TasksViewModelLogger(
            getAllTasksUseCase = caseLogger,
            addTaskUseCase = addTaskUseCase,
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

        addTaskUseCase.results = listOf(
            AddTaskUseCase.Result.Complete
        )

        getAllTasksUseCase.results = listOf(
            GetAllTasksUseCase.Result.Loaded(
                listOf(
                    FakeTask(
                        description = description.text
                    )
                )
            )
        )

        // Act
        viewModel.apply {

            addTask(
                description = description.text,
            )

            getAllTasks()

        }

        dispatcher.scheduler.advanceUntilIdle()

        val actual = viewModel.tasks

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

        getAllTasksUseCase.results = listOf(
            GetAllTasksUseCase.Result.Loaded(
                tasks = tasks
            ),
        )

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

//        dispatcher.scheduler.advanceUntilIdle()

        val actual = viewModel.tasks

        println("Actual: $actual")

        // Assert
        assertEquals(expected, actual)
    }

}
