package dev.ericd.todoey.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import dev.ericd.todoey.R
import dev.ericd.todoey.ui.resource.StringResource

interface TopBarComponent {

    interface State {

        val navigationButtonState: IconButtonComponent.State?

        val titleResource: StringResource

        val actions: List<IconButtonComponent.State>

    }

}

class TopBarComponentState(
    initializer: TopBarComponentState.() -> Unit = {}
) : TopBarComponent.State {

    override var navigationButtonState: IconButtonComponent.State? by
    mutableStateOf(null)

    override var titleResource: StringResource by mutableStateOf(
        StringResource.Id(R.string.empty)
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

            val title = when (
                val resource = state.titleResource
            ) {

                is StringResource.Id     -> {
                    stringResource(id = resource.value)
                }

                is StringResource.String -> {
                    resource.value
                }

            }

            Text(
                text = title,
            )

        },
        actions = {

            state.actions.forEach { buttonState ->

                IconButtonComponent(buttonState)

            }

        }

    )

}