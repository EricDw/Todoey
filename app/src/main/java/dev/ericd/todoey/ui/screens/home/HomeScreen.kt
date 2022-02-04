package dev.ericd.todoey.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericd.todoey.R
import dev.ericd.todoey.ui.components.*
import dev.ericd.todoey.ui.theme.TodoeyTheme

interface HomeScreen {

    interface State {

        val tasks: List<TaskComponent.State>

        val extendedFABState: FABComponent.State.Extended

    }

}

class HomeScreenState : HomeScreen.State {

    override var tasks: List<TaskComponent.State> by mutableStateOf(
        emptyList()
    )

    override var extendedFABState: ExtendedFABComponentState by mutableStateOf(
        ExtendedFABComponentState(
            iconId = R.drawable.ic_baseline_add_task_24,
            textId = R.string.label_add_task,
            isEnabled = true
        )
    )
}

@Composable
fun HomeScreen(
    state: HomeScreen.State = remember {
        HomeScreenState()
    }
) {
    TodoeyTheme {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                AnimatedVisibility(
                    visible = state.extendedFABState.isEnabled
                ) {

                    ExtendedFABComponent(
                        state = state.extendedFABState
                    )
                }

            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                items(state.tasks) { taskState ->

                    TaskCardComponent(
                        state = taskState,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillParentMaxWidth()
                    )

                }

            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {

    val state = remember {
        HomeScreenState().apply {
            tasks = listOf(
                TaskState(
                    description = AnnotatedString("Buy Milk")
                )
            )
        }
    }

    HomeScreen(state = state)

}
