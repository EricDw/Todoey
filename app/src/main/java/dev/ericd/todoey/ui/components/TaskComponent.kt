package dev.ericd.todoey.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericd.todoey.R
import dev.ericd.todoey.ui.theme.TodoeyTheme

interface TaskComponent {

    interface State {

        val title: AnnotatedString

        val details: AnnotatedString

        val completed: Boolean

        val deleteButtonState: IconButtonComponent.State

        val onCompletedChangeHandler: ((Boolean) -> Unit)?

    }

}

class TaskState(
    initializer: TaskState.() -> Unit = {}
) : TaskComponent.State {

    override var title: AnnotatedString by mutableStateOf(
        AnnotatedString("")
    )

    override var details: AnnotatedString by mutableStateOf(
        AnnotatedString("")
    )

    override var completed: Boolean by mutableStateOf(
        false
    )

    override val deleteButtonState = IconButtonComponentState {

        iconId = R.drawable.ic_baseline_delete_24

        descriptionId = R.string.description_delete_task

    }

    override var onCompletedChangeHandler: ((Boolean) -> Unit)? by
    mutableStateOf(null)

    init {
        initializer()
    }

    override fun equals(other: Any?): Boolean {

        if (other === this) return true
        if (other !is TaskComponent.State) return false
        if (other.completed != completed) return false
        if (other.details != details) return false

        return true

    }
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
            Column(Modifier.fillMaxWidth()) {
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
                        text = state.title
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    IconButtonComponent(state = state.deleteButtonState)

                }

                Row(Modifier.fillMaxWidth()) {
                    Text(text = state.details)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {

    val state = remember {
        TaskState {
            title = AnnotatedString("Buy Milk")
            details = AnnotatedString("From the store")
        }
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
