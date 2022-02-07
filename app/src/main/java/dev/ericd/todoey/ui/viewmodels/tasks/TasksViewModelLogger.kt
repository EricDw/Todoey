package dev.ericd.todoey.ui.viewmodels.tasks

import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.ui.components.TaskState

class TasksViewModelLogger(
    repository: Task.Repository,
    private val logger: Logger,
) : TasksViewModel(
    repository = repository
) {

    private val tag = TasksViewModel::class.simpleName

    override fun loadAllTasks() {
        logger.logMessage("$tag: Loading all Tasks")
        super.loadAllTasks()
    }

    override fun addTask(description: String) {
        logger.logMessage("$tag: Adding a Task")
        super.addTask(description)
    }

    override fun presentFailure(
        cause: Throwable,
    ) {
        logger.logMessage("$tag: Presenting Failure")
        super.presentFailure(cause)
    }

    override fun presentLoading() {
        logger.logMessage("$tag: Presenting Loading")
        super.presentLoading()
    }

    override fun presentTasks(
        tasks: List<Task>,
    ) {
        logger.logMessage("$tag: Presenting Tasks")
        super.presentTasks(tasks)
    }

    override fun mapTasksToUIState(tasks: List<Task>): List<TaskState> {
        logger.logMessage("$tag: Mapping Tasks to TaskStates")
        return super.mapTasksToUIState(tasks).map { taskState ->

            val originalHandler = taskState.onDeleteClickHandler

            taskState.apply {
                onDeleteClickHandler = {
                    logger.logMessage("$tag: Delete Task Clicked")
                    originalHandler()
                }
            }
        }
    }
}