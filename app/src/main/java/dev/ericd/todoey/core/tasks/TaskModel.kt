package dev.ericd.todoey.core.tasks

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class TaskModel(
    override val description: String = "",
    override val id: Task.Id = Task.Id()
) : Task {

    private val backingIsComplete =
        MutableStateFlow(false)

    override val isComplete: StateFlow<Boolean>
        get() = backingIsComplete

    override suspend fun complete() {
        backingIsComplete.value = true
    }
}