package dev.ericd.todoey.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dev.ericd.todoey.databinding.ComposeViewLayoutBinding

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