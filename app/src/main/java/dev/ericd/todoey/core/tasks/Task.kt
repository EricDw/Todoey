package dev.ericd.todoey.core.tasks

import dev.ericd.todoey.common.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface Task {

    val id: Id

    val title: String

    val details: String

    val isComplete: StateFlow<Boolean>

    suspend fun complete()

    @JvmInline
    value class Id(
        val value: String = UUID.randomUUID().toString()
    )

    interface Repository: IRepository<Id, Task> {

        val taskFlow: StateFlow<List<Task>>

    }

}
