package dev.ericd.todoey.ui.screens.addtask.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.data.repositories.fakes.FakeTaskRepository
import dev.ericd.todoey.ui.screens.addtask.AddTaskScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
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

    @Test
    fun `Emits NavigateBack SideEffect upon save`() = runTest {
        // Arrange
        val expected = AddTaskScreen.SideEffect.NavigateBack

        var actual: AddTaskScreen.SideEffect? = null

        viewModel.sideEffects.take(1).onEach {
            actual = it
        }.launchIn(this)

        // Act
        viewModel.state.run {

            titleValueChangeHandler(TextFieldValue("Hello"))

            topBarState.actions.first().clickHandler()

        }

        dispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun `Can not save a Task with no title`() = runTest {
        // Arrange
        val theSaveButton = viewModel.state.topBarState.actions.first()

        assertFalse(
            "The save button starts out as disabled",
            theSaveButton.isEnabled
        )

        // Act & Assert
        viewModel.state.run {

            titleValueChangeHandler(
                TextFieldValue(
                    "Hello"
                )
            )

            assertTrue(
                "The save button is enabled",
                theSaveButton.isEnabled
            )

            titleValueChangeHandler(
                TextFieldValue(
                    ""
                )
            )


            assertFalse(
                "The save button is disabled",
                theSaveButton.isEnabled
            )

        }

    }


}