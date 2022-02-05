package dev.ericd.todoey.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger

class TasksViewModelFactory(
    private val taskRepository: Task.Repository,
    private val logger: Logger,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            TasksViewModel::class.java       -> {
                TasksViewModel(
                    repository = taskRepository,
                )
            }

            TasksViewModelLogger::class.java -> {
                TasksViewModelLogger(
                    repository = taskRepository,
                    logger = logger
                )
            }

            else                                  -> {
                throw IllegalArgumentException("Unsupported model class: $modelClass")
            }
        } as T
    }
}
