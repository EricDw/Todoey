package dev.ericd.todoey.core.user

class NonAuthenticatedUser : User.NonAuthenticated {
    override val id: User.Id = User.Id()
}