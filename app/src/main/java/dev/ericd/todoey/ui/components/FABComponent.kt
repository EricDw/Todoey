package dev.ericd.todoey.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.ericd.todoey.R
import dev.ericd.todoey.ui.resource.ImageResource
import dev.ericd.todoey.ui.resource.StringResource

interface FABComponent {

    interface State : IconButtonComponent.State {

        interface Extended : State {
            val textResource: StringResource
        }

    }

}

class ExtendedFABComponentState(
    initializer: ExtendedFABComponentState.() -> Unit = {}
) : FABComponent.State.Extended {

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

    override var textResource by mutableStateOf(
        StringResource.Id(
            R.string.empty
        )
    )

    override var isEnabled by mutableStateOf(
        false
    )

    override var clickHandler by mutableStateOf(
        {}
    )

    init {
        initializer()
    }

}

@Composable
fun ExtendedFABComponent(
    state: FABComponent.State.Extended
) {
    ExtendedFloatingActionButton(
        onClick = state.clickHandler,
        icon = {

            val description = when (val resource = state.descriptionResource) {

                is StringResource.String -> {
                    resource.value
                }

                is StringResource.Id     -> {
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

                is ImageResource.Id     -> {

                    Icon(
                        painter = painterResource(id = resource.value),
                        contentDescription = description
                    )

                }

            }

        },
        text = {

            val value = when (
                val resource = state.descriptionResource
            ) {

                is StringResource.String -> {
                    resource.value
                }

                is StringResource.Id     -> {
                    stringResource(id = resource.value)
                }

            }

            Text(
                text = value
            )

        }

    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExtendedFABComponentPreview() {

    val state = remember {

        ExtendedFABComponentState {

            iconResource = ImageResource.Id(R.drawable.ic_baseline_add_task_24)

            textResource = StringResource.Id(R.string.label_add_task)

        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ExtendedFABComponent(state = state)
    }


}