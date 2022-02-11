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
import dev.ericd.todoey.ui.resource.ImageResource
import dev.ericd.todoey.ui.resource.StringResource

interface IconButtonComponent {

    interface State {

        val iconResource: ImageResource

        val descriptionResource: StringResource

        val isEnabled: Boolean

        val clickHandler: () -> Unit

    }

}

class IconButtonComponentState(
    initializer: IconButtonComponentState.() -> Unit = {}
) : IconButtonComponent.State {

    override var iconResource: ImageResource by mutableStateOf(
        ImageResource.Id(
            R.drawable.ic_baseline_image_24
        )
    )

    override var descriptionResource: StringResource by mutableStateOf(
        StringResource.Id(
            R.string.empty
        )
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

        val description = when (val resource = state.descriptionResource) {

            is StringResource.String -> {
                resource.value
            }

            is StringResource.Id -> {
                stringResource(id = resource.value)
            }

        }

        when (val resource = state.iconResource) {

            is ImageResource.Bitmap -> {

                Icon(
                    resource.value,
                    contentDescription = description
                )

            }

            is ImageResource.Id -> {

                Icon(
                    painter = painterResource(id = resource.value),
                    contentDescription = description
                )

            }

        }

    }

}