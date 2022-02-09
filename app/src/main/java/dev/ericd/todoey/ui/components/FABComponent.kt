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

interface FABComponent {

    interface State: IconButtonComponent.State {

        interface Extended : State {
            val textId: Int
        }

    }

}

 class ExtendedFABComponentState(
    initializer: ExtendedFABComponentState.() -> Unit = {}
) : FABComponent.State.Extended {

    override var iconId by mutableStateOf(
        R.drawable.ic_launcher_foreground
    )

     override val descriptionId by mutableStateOf(
        R.string.empty
    )

    override var textId by mutableStateOf(
        R.string.empty
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

            Icon(
                painter = painterResource(id = state.iconId),
                contentDescription = stringResource(id = state.descriptionId)
            )

        },
        text = {
            Text(
                text = stringResource(id = state.textId)
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExtendedFABComponentPreview() {

    val state = remember {

        ExtendedFABComponentState {

            iconId = R.drawable.ic_baseline_add_task_24

            textId = R.string.label_add_task

        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ExtendedFABComponent(state = state)
    }


}