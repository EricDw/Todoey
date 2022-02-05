package dev.ericd.todoey.data.repositories.fakes

import dev.ericd.todoey.core.tasks.Task

class FakeTaskRepository: Task.Repository {

    private val taskMap = mutableMapOf<Task.Id, Task>()

    override fun getAll(): List<Task> {
        return taskMap.values.toList()
    }

    override fun insert(task: Task) {
        taskMap[task.id] = task
    }

}
