package dev.ericd.todoey.ui.screens.addtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ericd.todoey.R
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.core.tasks.TaskModel
import dev.ericd.todoey.ui.components.IconButtonComponentState
import dev.ericd.todoey.ui.screens.addtask.AddTaskScreen
import dev.ericd.todoey.ui.screens.addtask.AddTaskScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

open class AddTaskViewModel(
    private val repository: Task.Repository
) : ViewModel() {

    private val backingSideEffects =
        MutableSharedFlow<AddTaskScreen.SideEffect>()

    val sideEffects: Flow<AddTaskScreen.SideEffect> =
        backingSideEffects.asSharedFlow()

    private val backingState = AddTaskScreenState {

        topBarState.navigationButtonState =
            IconButtonComponentState {

                iconId = R.drawable.ic_baseline_arrow_back_24

                isEnabled = true

                clickHandler = {
                    viewModelScope.launch {
                        backingSideEffects.emit(
                            AddTaskScreen.SideEffect.NavigateBack
                        )
                    }
                }
            }

        topBarState.actions += listOf(
            IconButtonComponentState {

                iconId = R.drawable.ic_baseline_save_24

                descriptionId = R.string.description_save_task

                isEnabled = titleValue.text.isNotBlank()

                titleValueChangeHandler = { newValue ->

                    titleValue = newValue

                    isEnabled = newValue.text.isNotBlank()

                }

                clickHandler = {

                    viewModelScope.launch {

                        val task = TaskModel(
                            title = titleValue.text,
                            details = detailsValue.text
                        )

                        repository.insert(task)

                        backingSideEffects.emit(
                            AddTaskScreen.SideEffect.NavigateBack
                        )

                    }

                }

            }
        )

    }

    val state: AddTaskScreen.State
        get() = backingState

}