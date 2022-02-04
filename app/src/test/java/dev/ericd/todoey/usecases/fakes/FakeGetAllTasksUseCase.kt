package dev.ericd.todoey.usecases.fakes

import dev.ericd.todoey.usecases.GetAllTasksUseCase

open class FakeGetAllTasksUseCase : GetAllTasksUseCase {

    private var listener: GetAllTasksUseCase.Listener? = null

    open var results: List<GetAllTasksUseCase.Result> = emptyList()

    override fun setListener(listener: GetAllTasksUseCase.Listener) {
        this.listener = listener
    }

    override fun execute() {

        listener?.run {

            results.forEach(::onResult)

        }

    }

}