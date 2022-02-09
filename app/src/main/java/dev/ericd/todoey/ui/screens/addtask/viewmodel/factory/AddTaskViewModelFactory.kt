package dev.ericd.todoey.ui.screens.addtask.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.ui.screens.addtask.viewmodel.AddTaskViewModel

class AddTaskViewModelFactory(
    private val taskRepository: Task.Repository,
    private val logger: Logger,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            AddTaskViewModel::class.java -> {
                AddTaskViewModel(
                    repository = taskRepository,
                )
            }

//            TasksViewModelLogger::class.java -> {
//                TasksViewModelLogger(
//                    repository = taskRepository,
//                    logger = logger
//                )
//            }

            else                                  -> {
                throw IllegalArgumentException("Unsupported model class: $modelClass")
            }
        } as T
    }
}
