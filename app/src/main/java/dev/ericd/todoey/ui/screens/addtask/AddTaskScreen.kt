package dev.ericd.todoey.ui.screens.addtask

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericd.todoey.ui.theme.TodoeyTheme

interface AddTaskScreen {
    interface State {

    }
}

class AddTaskScreenState : AddTaskScreen.State

@Composable
fun AddTaskScreen(
    state: AddTaskScreen.State
) {
    TodoeyTheme() {
        Scaffold { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    elevation = 10.dp
                ) {
                    Box(
                        modifier = Modifier.padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "TODO")
                    }
                }
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
