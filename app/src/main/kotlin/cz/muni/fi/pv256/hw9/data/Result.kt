package cz.muni.fi.pv256.hw9.data

class Result<out T> private constructor(
    val value: T?,
    val state: State,
    val exception: Throwable? = null
) {

    companion object {
        fun <T> loading(value: T): Result<T> =
            Result(value, State.LOADING)

        fun <T> success(value: T): Result<T> =
            Result(value, State.SUCCESS)

        fun <T> failure(exception: Throwable, value: T? = null): Result<T> =
            Result(value, State.ERROR, exception)
    }

    enum class State {
        LOADING, SUCCESS, ERROR
    }
}
