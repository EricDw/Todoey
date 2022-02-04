package dev.ericd.todoey.core.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TheTaskModel {

    private lateinit var dispatcher: TestDispatcher

    private lateinit var theTask: Task

    @Before
    fun setUp() {
        dispatcher = StandardTestDispatcher().apply {
            Dispatchers.setMain(this)
        }

        theTask = TaskModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Can be completed`() = runTest {
        // Arrange
        val expected = true

        // Act
        theTask.complete()

        val actual = theTask.isComplete.value

        // Assert
        assertEquals(expected, actual)
    }

}