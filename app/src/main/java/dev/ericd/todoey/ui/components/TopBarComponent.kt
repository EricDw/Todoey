package dev.ericd.todoey.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import dev.ericd.todoey.R

interface TopBarComponent {

    interface State {

        val navigationButtonState: IconButtonComponent.State?

        val titleId: Int

        val actions: List<IconButtonComponent.State>

    }

}

class TopBarComponentState(
    initializer: TopBarComponentState.() -> Unit = {}
) : TopBarComponent.State {

    override var navigationButtonState: IconButtonComponent.State? by
    mutableStateOf(null)

    override var titleId: Int by mutableStateOf(
        R.string.empty
    )

    override val actions: MutableList<IconButtonComponent.State> =
    mutableStateListOf()

    init {
        initializer()
    }

}

@Composable
fun TopBarComponent(
    state: TopBarComponent.State
) {
    TopAppBar(
        navigationIcon =
        state.navigationButtonState?.let { buttonState ->
            {
                IconButtonComponent(buttonState)
            }
        },
        title = {

            Text(
                text = stringResource(
                    id = state.titleId
                ),
            )

        },
        actions = {

            state.actions.forEach { buttonState ->

                IconButtonComponent(buttonState)

            }

        }

    )

}