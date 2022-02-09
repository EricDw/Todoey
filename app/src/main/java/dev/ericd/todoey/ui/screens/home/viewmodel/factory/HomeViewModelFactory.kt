package dev.ericd.todoey.ui.screens.home.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.ui.screens.home.viewmodel.HomeViewModel
import dev.ericd.todoey.ui.screens.home.viewmodel.HomeViewModelLogger

class HomeViewModelFactory(
    private val taskRepository: Task.Repository,
    private val logger: Logger,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            HomeViewModel::class.java       -> {
                HomeViewModel(
                    repository = taskRepository,
                )
            }

            HomeViewModelLogger::class.java -> {
                HomeViewModelLogger(
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
