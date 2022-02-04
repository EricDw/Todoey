package dev.ericd.todoey.core.user

import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface User {

    val id: Id

    val role: StateFlow<Role>

    fun setRole(newRole: Role)

    @JvmInline
    value class Id(
        val value: String = UUID.randomUUID().toString()
    )

    sealed class Role {

        object Trial: Role()

        object Paid: Role()

    }

    interface Preferences {

    }

    interface Service {

    }

}