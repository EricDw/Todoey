package dev.ericd.todoey.ui.screens.addtask.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.data.repositories.fakes.FakeTaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TheAddTaskViewModel {

    private lateinit var viewModel: AddTaskViewModel

    private lateinit var repository: Task.Repository

    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setUp() {

        dispatcher = StandardTestDispatcher().apply {
            Dispatchers.setMain(this)
        }

        repository = FakeTaskRepository()

        viewModel = AddTaskViewModel(
            repository = repository
        )

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Can save a Task`() = runTest {
        // Arrange
        val expected = "Hello" to "World"

        assertTrue(
            "The repository starts empty",
            repository.getAll().isEmpty()
        )

        // Act
        viewModel.state.run {

            titleValueChangeHandler(
                TextFieldValue(
                    expected.first
                )
            )

            detailsValueChangeHandler(
                TextFieldValue(
                    expected.second
                )
            )

            topBarState.actions.first().clickHandler()

        }

        dispatcher.scheduler.advanceUntilIdle()

        val tasks = repository.getAll()

        assertTrue(
            "There is only one Task in the repository",
            tasks.size == 1
        )

        val actual = with(tasks.first()) {
            title to details
        }

        // Assert
        assertEquals(expected, actual)
    }
    
}