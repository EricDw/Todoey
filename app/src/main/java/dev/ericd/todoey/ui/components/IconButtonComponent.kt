package dev.ericd.todoey.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dev.ericd.todoey.R

interface IconButtonComponent {

    interface State {

        val iconId: Int

        val descriptionId: Int

        val isEnabled: Boolean

        val clickHandler: () -> Unit

    }

}

class IconButtonComponentState(
    initializer: IconButtonComponentState.() -> Unit = {}
) : IconButtonComponent.State {

    override var iconId: Int by mutableStateOf(
        R.drawable.ic_baseline_image_24
    )

    override var descriptionId: Int by mutableStateOf(
        R.string.empty
    )

    override var isEnabled: Boolean by mutableStateOf(
        false
    )

    override var clickHandler: () -> Unit by mutableStateOf(
        {}
    )

    init {
        initializer()
    }

}


@Composable
fun IconButtonComponent(
    state: IconButtonComponent.State
) {

    IconButton(
        onClick = state.clickHandler,
        enabled = state.isEnabled,
    ) {

        Icon(
            painter = painterResource(id = state.iconId),
            contentDescription = stringResource(
                id = state.descriptionId
            )
        )

    }

}