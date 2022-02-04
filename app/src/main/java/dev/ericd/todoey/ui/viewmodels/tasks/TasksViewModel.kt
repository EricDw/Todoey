package dev.ericd.todoey.ui.viewmodels.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.core.tasks.TaskModel
import dev.ericd.todoey.ui.components.TaskComponent
import dev.ericd.todoey.ui.components.TaskState
import dev.ericd.todoey.usecases.AddTaskUseCase
import dev.ericd.todoey.usecases.GetAllTasksUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class TasksViewModel(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    private var backingTasks: List<TaskComponent.State> by mutableStateOf(
        emptyList()
    )

    val tasks: List<TaskComponent.State>
        get() = backingTasks

    open fun getAllTasks() {

        getAllTasksUseCase.apply {

            setListener { theResult ->

                when (theResult) {

                    is GetAllTasksUseCase.Result.Failure -> {

                        presentFailure(theResult.cause)

                    }

                    is GetAllTasksUseCase.Result.Loaded  -> {

                        presentTasks(theResult.tasks)

                    }

                    is GetAllTasksUseCase.Result.Loading -> {

                        presentLoading()

                    }

                }

            }

        }.execute()

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

        addTaskUseCase.execute(
            task
        ).onEach { theResult ->

            when (theResult) {

                is AddTaskUseCase.Result.Complete -> {
                    getAllTasks()
                }

                is AddTaskUseCase.Result.Failure  -> {
                    TODO()
                }

                is AddTaskUseCase.Result.Running  -> {


                }

            }

        }.launchIn(viewModelScope)

    }

}