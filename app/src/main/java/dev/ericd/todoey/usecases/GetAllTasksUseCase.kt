package dev.ericd.todoey.usecases

import dev.ericd.todoey.common.usecase.LegacyUseCase
import dev.ericd.todoey.common.usecase.UseCase
import dev.ericd.todoey.core.tasks.Task
import kotlinx.coroutines.flow.Flow

interface GetAllTasksUseCase : LegacyUseCase {

    fun setListener(listener: Listener)

    sealed class Result {

        object Loading : Result()

        class Loaded(
            val tasks: List<Task>
        ) : Result()

        class Failure(
            val cause: Throwable
        ) : Result()

    }

    fun interface Listener {

        fun onResult(result: Result)

    }

}
