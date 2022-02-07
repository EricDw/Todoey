package dev.ericd.todoey.controllers

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import dev.ericd.todoey.BuildConfig
import dev.ericd.todoey.TodoeyApplication
import dev.ericd.todoey.ui.screens.addtask.AddTaskScreen
import dev.ericd.todoey.ui.screens.addtask.AddTaskScreenState
import dev.ericd.todoey.ui.viewmodels.factories.TasksViewModelFactory
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger

class AddTaskScreenFragment : ComposeFragment() {

    private lateinit var viewModel: TasksViewModel

    private lateinit var state: AddTaskScreenState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProvider = ViewModelProvider(
            this,
            TasksViewModelFactory(
                TodoeyApplication.taskRepository,
                TodoeyApplication.logger
            )
        )

        val modelClass = if (BuildConfig.DEBUG) {
            TasksViewModelLogger::class.java
        } else {
            TasksViewModel::class.java
        }

        viewModel = viewModelProvider.get(modelClass)

        state = AddTaskScreenState()

        composeView.setContent {
            AddTaskScreen(state = state)
        }

    }

}