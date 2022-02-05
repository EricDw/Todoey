package dev.ericd.todoey.core.user

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserModel(
    override val id: User.Id = User.Id(),
    role: User.Role = User.Role.Trial
) : User {

    private var backingRole = MutableStateFlow(role)

    override val role: StateFlow<User.Role> =
        backingRole.asStateFlow()

    override fun setRole(newRole: User.Role) {
        backingRole.value = newRole
    }

}