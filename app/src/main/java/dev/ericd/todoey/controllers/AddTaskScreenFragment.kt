package dev.ericd.todoey.controllers

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import dev.ericd.todoey.BuildConfig
import dev.ericd.todoey.TodoeyApplication
import dev.ericd.todoey.ui.screens.addtask.AddTaskScreen
import dev.ericd.todoey.ui.screens.addtask.viewmodel.AddTaskViewModel
import dev.ericd.todoey.ui.screens.addtask.viewmodel.factory.AddTaskViewModelFactory

class AddTaskScreenFragment : ComposeFragment() {

    private lateinit var viewModel: AddTaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProvider = ViewModelProvider(
            this,
            AddTaskViewModelFactory(
                TodoeyApplication.taskRepository,
                TodoeyApplication.logger
            )
        )

        val modelClass = if (BuildConfig.DEBUG) {
            AddTaskViewModel::class.java
        } else {
            AddTaskViewModel::class.java
        }

        viewModel = viewModelProvider.get(modelClass)


        composeView.setContent {
            AddTaskScreen(state = viewModel.state)
        }

    }

}