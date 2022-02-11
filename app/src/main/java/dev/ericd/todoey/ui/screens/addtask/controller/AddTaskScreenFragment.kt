package dev.ericd.todoey.ui.screens.addtask.controller

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.ericd.todoey.BuildConfig
import dev.ericd.todoey.controllers.TodoeyApplication
import dev.ericd.todoey.ui.base.ComposeFragment
import dev.ericd.todoey.ui.screens.addtask.AddTaskScreen
import dev.ericd.todoey.ui.screens.addtask.viewmodel.AddTaskViewModel
import dev.ericd.todoey.ui.screens.addtask.viewmodel.factory.AddTaskViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddTaskScreenFragment : ComposeFragment() {

    private lateinit var viewModel: AddTaskViewModel

    private var sideEffectJob: Job? = null

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

        sideEffectJob?.cancel()

        viewModel.sideEffects.onEach { theSideEffect ->
            when (theSideEffect) {
                AddTaskScreen.SideEffect.NavigateBack -> {
                    findNavController().popBackStack()
                }
            }
        }.launchIn(lifecycleScope)

        composeView.setContent {
            AddTaskScreen(state = viewModel.state)
        }

    }

    override fun onDestroyView() {
        sideEffectJob?.cancel()
        sideEffectJob = null
        super.onDestroyView()
    }
}