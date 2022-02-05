package dev.ericd.todoey.data.repositories

import dev.ericd.todoey.core.tasks.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskRepository : Task.Repository {

    private val taskMap = mutableMapOf<Task.Id, Task>()

    private val backingTaskFlow = MutableStateFlow(
        emptyList<Task>()
    )

    override val taskFlow: StateFlow<List<Task>> =
        backingTaskFlow.asStateFlow()

    override fun getAll(): List<Task> {
        return taskMap.values.toList()
    }

    override fun insert(entity: Task) {
        taskMap[entity.id] = entity
        backingTaskFlow.value = taskMap.values.toList()
    }

}