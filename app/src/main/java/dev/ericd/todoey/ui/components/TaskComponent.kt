package dev.ericd.todoey.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericd.todoey.R
import dev.ericd.todoey.ui.theme.TodoeyTheme

interface TaskComponent {

    interface State {

        val description: AnnotatedString
        val completed: Boolean

        val deleteIconId: Int
        val deleteIconDescriptionId: Int
        val deleteEnabled: Boolean

        val onCompletedChangeHandler: ((Boolean) -> Unit)?
        val onDeleteClickHandler: () -> Unit

    }

}

data class TaskState(
    override val description: AnnotatedString = AnnotatedString(""),
    override val completed: Boolean = false,
    override val deleteIconId: Int = R.drawable.ic_baseline_delete_24,
    override val deleteIconDescriptionId: Int = R.string.description_delete_task,
) : TaskComponent.State {

    override var deleteEnabled: Boolean by mutableStateOf(
        false
    )

    override var onCompletedChangeHandler: ((Boolean) -> Unit)? =
        null

    override var onDeleteClickHandler: () -> Unit = { }

}

@Composable
fun TaskCardComponent(
    state: TaskComponent.State,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Checkbox(
                    checked = state.completed,
                    onCheckedChange = state.onCompletedChangeHandler
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = state.description
                )

                Spacer(modifier = Modifier.width(4.dp))

                IconButton(
                    onClick = state.onDeleteClickHandler,
                    enabled = state.deleteEnabled
                ) {

                    Icon(
                        painter = painterResource(id = state.deleteIconId),
                        contentDescription = stringResource(id = state.deleteIconDescriptionId)
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {

    val state = remember {
        TaskState(
            description = AnnotatedString("Buy Milk")
        )
    }

    TodoeyTheme {
        Surface {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                TaskCardComponent(state)
            }
        }
    }
}
