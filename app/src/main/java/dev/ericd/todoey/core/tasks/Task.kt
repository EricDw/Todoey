package dev.ericd.todoey.core.tasks

import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface Task {

    val id: Id

    val description: String

    val isComplete: StateFlow<Boolean>

    suspend fun complete()

    @JvmInline
    value class Id(
        val value: String = UUID.randomUUID().toString()
    )

    interface Repository {

        fun getAll(): List<Task>

        fun insert(task: Task)

    }

}
