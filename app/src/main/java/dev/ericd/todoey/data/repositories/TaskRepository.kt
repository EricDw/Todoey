package dev.ericd.todoey.data.repositories

import dev.ericd.todoey.core.tasks.Task

class TaskRepository : Task.Repository {

    private val taskMap = mutableMapOf<Task.Id, Task>()

    override fun getAll(): List<Task> {
        return taskMap.values.toList()
    }

    override fun insert(task: Task) {
        taskMap[task.id] = task
    }
}