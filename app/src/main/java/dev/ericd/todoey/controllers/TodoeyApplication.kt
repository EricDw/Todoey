package dev.ericd.todoey.controllers

import android.app.Application
import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.data.repositories.TaskRepository
import dev.ericd.todoey.logs.AndroidLogger

class TodoeyApplication : Application() {

    companion object {

        private var backingTaskRepository: Task.Repository? = null

        val taskRepository: Task.Repository
            get() = backingTaskRepository!!

        private var backingLogger: Logger? = null

        val logger: Logger
            get() = backingLogger!!

    }

    override fun onCreate() {
        super.onCreate()

        backingTaskRepository = TaskRepository()

        backingLogger = AndroidLogger()

    }
}