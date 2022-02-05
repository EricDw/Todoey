package dev.ericd.todoey.ui.viewmodels.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.core.tasks.TaskModel
import dev.ericd.todoey.ui.components.TaskComponent
import dev.ericd.todoey.ui.components.TaskState

open class TasksViewModel(
    private val repository: Task.Repository
) : ViewModel() {

    private var backingTasks: List<TaskComponent.State> by mutableStateOf(
        emptyList()
    )

    val tasks: List<TaskComponent.State>
        get() = backingTasks

    open fun getAllTasks() {

        presentTasks(
            repository.getAll()
        )

    }

    protected open fun presentFailure(
        cause: Throwable
    ) {

    }

    protected open fun presentTasks(
        tasks: List<Task>
    ) {

        backingTasks = tasks.map { theTask ->
            TaskState(
                description = AnnotatedString(theTask.description),
            )
        }

    }

    protected open fun presentLoading() {

    }

    fun addTask(
        description: String,
    ) {

        val task = TaskModel(
            description = description,
        )

        repository.insert(
            task
        )

    }

}