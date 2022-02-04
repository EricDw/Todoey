package dev.ericd.todoey.usecases

import dev.ericd.todoey.core.tasks.Task

class GetAllTasksUseCaseSync(
    private val repository: Task.Repository
) : GetAllTasksUseCase {

    private var listener: GetAllTasksUseCase.Listener? = null

    override fun execute() {

        listener?.onResult(
            GetAllTasksUseCase.Result.Loading
        )

        val tasks = repository.getAll()

        listener?.onResult(
            GetAllTasksUseCase.Result.Loaded(
                tasks
            )
        )

    }

    override fun setListener(
        listener: GetAllTasksUseCase.Listener
    ) {
        this.listener = listener
    }

}