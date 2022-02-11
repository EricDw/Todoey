package dev.ericd.todoey.ui.screens.home.controller

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.ericd.todoey.BuildConfig
import dev.ericd.todoey.R
import dev.ericd.todoey.controllers.TodoeyApplication
import dev.ericd.todoey.ui.base.ComposeFragment
import dev.ericd.todoey.ui.screens.home.HomeScreen
import dev.ericd.todoey.ui.screens.home.HomeScreenState
import dev.ericd.todoey.ui.screens.home.viewmodel.factory.HomeViewModelFactory
import dev.ericd.todoey.ui.screens.home.viewmodel.HomeViewModel
import dev.ericd.todoey.ui.screens.home.viewmodel.HomeViewModelLogger
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeScreenFragment : ComposeFragment() {

    private lateinit var viewModel: HomeViewModel

    private lateinit var state: HomeScreenState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProvider = ViewModelProvider(
            this,
            HomeViewModelFactory(
                TodoeyApplication.taskRepository,
                TodoeyApplication.logger
            )
        )

        val modelClass = if (BuildConfig.DEBUG) {
            HomeViewModelLogger::class.java
        } else {
            HomeViewModel::class.java
        }

        viewModel = viewModelProvider.get(modelClass)

        state = HomeScreenState {

            tasks.addAll(viewModel.tasks.value)

            extendedFABState.clickHandler = {

                findNavController().navigate(
                    R.id.action_homeScreenFragment_to_addTaskScreenFragment2
                )

            }

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