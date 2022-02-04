package dev.ericd.todoey.data.repositories

import dev.ericd.todoey.core.tasks.Task

class TaskRepository : Task.Repository {

    private val tasks = mutableListOf<Task>()

    override fun getAll(): List<Task> {
        return tasks.toList()
    }

    override fun insert(task: Task) {
        tasks += task
    }
}