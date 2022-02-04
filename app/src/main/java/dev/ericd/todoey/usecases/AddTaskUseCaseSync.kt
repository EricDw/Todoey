package dev.ericd.todoey.usecases

import dev.ericd.todoey.core.tasks.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddTaskUseCaseSync(
    private val repository: Task.Repository
) : AddTaskUseCase {

    override fun execute(input: Task): Flow<AddTaskUseCase.Result> {

        return flow {

            emit(
                AddTaskUseCase.Result.Running
            )

            repository.insert(input)

            emit(
                AddTaskUseCase.Result.Complete
            )

        }

    }

}