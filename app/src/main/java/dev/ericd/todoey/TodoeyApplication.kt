package dev.ericd.todoey

import android.app.Application
import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.data.repositories.TaskRepository
import dev.ericd.todoey.usecases.AddTaskUseCase
import dev.ericd.todoey.usecases.AddTaskUseCaseSync
import dev.ericd.todoey.usecases.GetAllTasksUseCase
import dev.ericd.todoey.usecases.GetAllTasksUseCaseSync

class TodoeyApplication : Application() {

    companion object {

        private var backingGetAllTasksUseCase: GetAllTasksUseCase? = null

        val getAllTasksUseCase: GetAllTasksUseCase
            get() = backingGetAllTasksUseCase!!

        private var backingAddTaskUseCase: AddTaskUseCase? = null

        val addTaskUseCase: AddTaskUseCase
            get() = backingAddTaskUseCase!!

        private var backingLogger: Logger? = null

        val logger: Logger
            get() = backingLogger!!

    }

    private lateinit var tasksRepository: Task.Repository

    override fun onCreate() {
        super.onCreate()

        tasksRepository = TaskRepository()

        backingGetAllTasksUseCase = if (BuildConfig.DEBUG) {
            GetAllTasksUseCaseSync(tasksRepository)
        } else {
            GetAllTasksUseCaseSync(tasksRepository)
        }

        backingAddTaskUseCase = if (BuildConfig.DEBUG) {
            AddTaskUseCaseSync(tasksRepository)
        } else {
            AddTaskUseCaseSync(tasksRepository)
        }

        backingLogger = AndroidLogger()

    }
}