package dev.ericd.todoey.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger
import dev.ericd.todoey.usecases.AddTaskUseCase
import dev.ericd.todoey.usecases.GetAllTasksUseCase

class TasksViewModelFactory(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val logger: Logger
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            TasksViewModel::class.java       -> {
                TasksViewModel(
                    getAllTasksUseCase = getAllTasksUseCase,
                    addTaskUseCase = addTaskUseCase,
                )
            }

            TasksViewModelLogger::class.java -> {
                TasksViewModelLogger(
                    getAllTasksUseCase = getAllTasksUseCase,
                    addTaskUseCase = addTaskUseCase,
                    logger = logger
                )
            }

            else                                  -> {
                throw IllegalArgumentException("Unsupported model class: $modelClass")
            }
        } as T
    }
}
