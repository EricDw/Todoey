package dev.ericd.todoey.controllers

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.ericd.todoey.BuildConfig
import dev.ericd.todoey.R
import dev.ericd.todoey.TodoeyApplication
import dev.ericd.todoey.ui.screens.home.HomeScreen
import dev.ericd.todoey.ui.screens.home.HomeScreenState
import dev.ericd.todoey.ui.viewmodels.factories.TasksViewModelFactory
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeScreenFragment : ComposeFragment() {

    private lateinit var viewModel: TasksViewModel

    private lateinit var state: HomeScreenState

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

        state = HomeScreenState().apply {

            tasks.addAll(viewModel.tasks.value)

            extendedFABState = extendedFABState.copy(
                onClickHandler = {

                    findNavController().navigate(
                        R.id.action_homeScreenFragment_to_addTaskScreenFragment2
                    )

                }
            )
        }

        viewModel.tasks.onEach { newTasks ->
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {

                state.tasks.clear()
                state.tasks.addAll(newTasks)
            }
        }.launchIn(lifecycleScope)

        composeView.setContent {
            HomeScreen(
                state = state
            )
        }

        viewModel.loadAllTasks()

    }

}