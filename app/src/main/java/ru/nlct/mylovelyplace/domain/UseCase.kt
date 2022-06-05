package ru.nlct.mylovelyplace.domain

abstract class UseCase<in P, R> {

    operator fun invoke(parameters: P): R = execute(parameters)

    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R
}
