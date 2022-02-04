package dev.ericd.todoey.ui.viewmodels.tasks

import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.usecases.AddTaskUseCase
import dev.ericd.todoey.usecases.GetAllTasksUseCase

class TasksViewModelLogger(
    getAllTasksUseCase: GetAllTasksUseCase,
    addTaskUseCase: AddTaskUseCase,
    private val logger: Logger,
) : TasksViewModel(
    getAllTasksUseCase = getAllTasksUseCase,
    addTaskUseCase = addTaskUseCase
) {

    private val tag = TasksViewModel::class.simpleName

    override fun getAllTasks() {
        logger.logMessage("$tag: Getting all Tasks")
        super.getAllTasks()
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



}