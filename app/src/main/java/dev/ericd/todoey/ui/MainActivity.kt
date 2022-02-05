package dev.ericd.todoey.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.ericd.todoey.BuildConfig
import dev.ericd.todoey.TodoeyApplication
import dev.ericd.todoey.ui.screens.home.HomeScreen
import dev.ericd.todoey.ui.screens.home.HomeScreenState
import dev.ericd.todoey.ui.viewmodels.factories.TasksViewModelFactory
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TasksViewModel

    private lateinit var state: HomeScreenState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        viewModel.tasks.onEach { newTasks ->
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED))
                state.tasks = newTasks
        }.launchIn(lifecycleScope)

        state = HomeScreenState().apply {

            tasks = viewModel.tasks.value

            extendedFABState = extendedFABState.copy(
                onClickHandler = {

                    viewModel.addTask(
                        description = "Buy Meat"
                    )

                }
            )
        }

        setContent {
            HomeScreen(
                state = state
            )
        }

        viewModel.getAllTasks()

    }

}

