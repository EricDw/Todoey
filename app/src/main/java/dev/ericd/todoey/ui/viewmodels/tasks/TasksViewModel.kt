package dev.ericd.todoey.ui.viewmodels.tasks

import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.core.tasks.TaskModel
import dev.ericd.todoey.ui.components.TaskComponent
import dev.ericd.todoey.ui.components.TaskState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class TasksViewModel(
    private val repository: Task.Repository
) : ViewModel() {

    private var backingTasks = MutableStateFlow<List<TaskComponent.State>>(
        emptyList()
    )

    val tasks: StateFlow<List<TaskComponent.State>>
        get() = backingTasks

    private var taskLoader: Job? = null

    open fun loadAllTasks() {

        taskLoader?.cancel()

        taskLoader = repository.taskFlow
            .onEach(::presentTasks)
            .launchIn(viewModelScope)

    }

    protected open fun presentFailure(
        cause: Throwable
    ) {

    }

    protected open fun presentTasks(
        tasks: List<Task>
    ) {

        backingTasks.value = tasks.map { theTask ->
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

    override fun onCleared() {

        taskLoader?.cancel()
        taskLoader = null

        super.onCleared()

    }

}