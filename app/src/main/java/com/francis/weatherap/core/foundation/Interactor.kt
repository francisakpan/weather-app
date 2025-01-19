package com.francis.weatherap.core.foundation

/**
 * One shot interactor
 *
 * @param P parameters to configure the interactor execution
 * @param T the returned type
 */
interface Interactor<P : Any, T> {

    suspend operator fun invoke(params: P): T
}
