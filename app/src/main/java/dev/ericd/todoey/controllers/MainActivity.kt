package dev.ericd.todoey.controllers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity_layout)

    }

}

