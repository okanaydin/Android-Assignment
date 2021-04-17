package app.storytel.candidate.com.core

/**
 *  Resource is a generic class that contains data and status about loading this data.
 *  ref: https://developer.android.com/jetpack/guide#addendum
 */

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Success<T>(val data: T) : Resource<T>()
    class Failed<T>(val message: String) : Resource<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}
