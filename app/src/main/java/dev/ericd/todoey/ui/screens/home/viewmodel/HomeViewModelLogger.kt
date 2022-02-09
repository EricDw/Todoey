package dev.ericd.todoey.ui.screens.home.viewmodel

import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.ui.components.TaskState

class HomeViewModelLogger(
    repository: Task.Repository,
    private val logger: Logger,
) : HomeViewModel(
    repository = repository
) {

    private val tag = HomeViewModel::class.simpleName

    override fun loadAllTasks() {
        logger.logMessage("$tag: Loading all Tasks")
        super.loadAllTasks()
    }

    override fun addTask(details: String) {
        logger.logMessage("$tag: Adding a Task")
        super.addTask(details)
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

            val originalHandler = taskState.deleteButtonState.clickHandler

            taskState.apply {
                taskState.deleteButtonState.clickHandler = {
                    logger.logMessage("$tag: Delete Task Clicked")
                    originalHandler()
                }
            }
        }
    }
}