package dev.ericd.todoey.ui.screens.addtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericd.todoey.R
import dev.ericd.todoey.ui.components.IconButtonComponentState
import dev.ericd.todoey.ui.components.TopBarComponent
import dev.ericd.todoey.ui.components.TopBarComponentState
import dev.ericd.todoey.ui.theme.TodoeyTheme

interface AddTaskScreen {

    interface State {

        val topBarState: TopBarComponent.State

        val titleLabelId: Int

        val titleValue: TextFieldValue

        val titleValueChangeHandler: (TextFieldValue) -> Unit

        val detailsLabelId: Int

        val detailsValue: TextFieldValue

        val detailsValueChangeHandler: (TextFieldValue) -> Unit

    }

    sealed class SideEffect {

        object NavigateBack: SideEffect()

    }

}

class AddTaskScreenState(
    initializer: AddTaskScreenState.() -> Unit = {}
) : AddTaskScreen.State {

    override val topBarState = TopBarComponentState {

        navigationButtonState = IconButtonComponentState().apply {

            iconId = R.drawable.ic_baseline_arrow_back_24

            descriptionId = R.string.description_go_back

        }

        titleId = R.string.label_add_task

    }

    override var titleLabelId: Int by mutableStateOf(
            R.string.label_title
        )

    override var titleValue: TextFieldValue by mutableStateOf(
        TextFieldValue()
    )

    override var titleValueChangeHandler: (TextFieldValue) -> Unit = { newValue ->
        titleValue = newValue
    }

    override var detailsLabelId: Int =
        R.string.label_details

    override var detailsValue: TextFieldValue by mutableStateOf(
        TextFieldValue()
    )

    override var detailsValueChangeHandler: (TextFieldValue) -> Unit = { newValue ->
        detailsValue = newValue
    }

    init {
        initializer()
    }

}


@Composable
fun AddTaskScreen(
    state: AddTaskScreen.State
) {

    TodoeyTheme {

        Scaffold(
            topBar = {
                TopBarComponent(
                    state = state.topBarState
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            16.dp
                        ),
                    value = state.titleValue,
                    onValueChange = state.titleValueChangeHandler,
                    label = {
                        Text(text = stringResource(id = state.titleLabelId))
                    },

                    )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            16.dp
                        ),
                    value = state.detailsValue,
                    onValueChange = state.detailsValueChangeHandler,
                    label = {
                        Text(text = stringResource(id = state.detailsLabelId))
                    }
                )

            }

        }

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun AddTaskScreenPreview() {

    val state = remember {
        AddTaskScreenState()
    }

    AddTaskScreen(state = state)

}
