package ru.nct.mylovelyplace.domain

interface Mapper<T, R> {
    operator fun invoke(input: T): R
}