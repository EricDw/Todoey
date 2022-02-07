package dev.ericd.todoey.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.ericd.todoey.BuildConfig
import dev.ericd.todoey.TodoeyApplication
import dev.ericd.todoey.databinding.ComposeViewLayoutBinding
import dev.ericd.todoey.ui.screens.home.HomeScreen
import dev.ericd.todoey.ui.screens.home.HomeScreenState
import dev.ericd.todoey.ui.viewmodels.factories.TasksViewModelFactory
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModel
import dev.ericd.todoey.ui.viewmodels.tasks.TasksViewModelLogger
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class ComposeFragment : Fragment() {

    private var backingBinding: ComposeViewLayoutBinding? = null

    private val binding: ComposeViewLayoutBinding
        get() = backingBinding!!

    protected val composeView: ComposeView
        get() = binding.composeView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        backingBinding = ComposeViewLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBinding = null
    }
}