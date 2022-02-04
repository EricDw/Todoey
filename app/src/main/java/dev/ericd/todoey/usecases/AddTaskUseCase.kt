package dev.ericd.todoey.usecases

import dev.ericd.todoey.common.usecase.UseCase
import dev.ericd.todoey.core.tasks.Task
import kotlinx.coroutines.flow.Flow

interface AddTaskUseCase : UseCase<Task, Flow<AddTaskUseCase.Result>> {

    sealed class Result {

        object Running : Result()

        object Complete : Result()

        class Failure(
            val cause: Throwable
        ) : Result()

    }

}
