package dev.ericd.todoey.usecases.logs

import dev.ericd.todoey.common.logs.Logger
import dev.ericd.todoey.usecases.GetAllTasksUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class GetAllTasksUseCaseLogger(
    private val delegate: GetAllTasksUseCase,
    private val logger: Logger
) : GetAllTasksUseCase by delegate {

    private val tag = delegate::class.simpleName

    override fun execute() {

        logger.logMessage(
            "$tag: Executing"
        )

        delegate.execute()

    }

    override fun setListener(listener: GetAllTasksUseCase.Listener) {

        delegate.setListener { theResult ->

            logger.logMessage(
                "$tag: Emitting: $theResult"
            )

            listener.onResult(theResult)

        }

    }

}
