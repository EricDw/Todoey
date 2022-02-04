package dev.ericd.todoey.core.tasks.fakes

import dev.ericd.todoey.core.tasks.Task
import kotlinx.coroutines.flow.StateFlow

data class FakeTask(
    override val description: String,
    override val id: Task.Id = Task.Id()
): Task {

    override val isComplete: StateFlow<Boolean>
        get() = TODO("Not yet implemented")

    override suspend fun complete() {
        TODO("Not yet implemented")
    }

}