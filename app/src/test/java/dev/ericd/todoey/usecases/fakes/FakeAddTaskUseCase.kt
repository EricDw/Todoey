package dev.ericd.todoey.usecases.fakes

import dev.ericd.todoey.core.tasks.Task
import dev.ericd.todoey.usecases.AddTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class FakeAddTaskUseCase : AddTaskUseCase {

    open var results: List<AddTaskUseCase.Result> = emptyList()

    override fun execute(input: Task): Flow<AddTaskUseCase.Result> {

        return flow {

            results.forEach { theResult ->

                emit(theResult)

            }

        }

    }

}
