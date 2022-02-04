package dev.ericd.todoey.common.usecase

fun interface UseCase<I, O> {

    fun execute(input: I): O

}