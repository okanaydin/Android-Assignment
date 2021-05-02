package app.storytel.candidate.com.features.core

/**
 * Layout View State is a class that contains status about the data for tracking on the UI.
 */
class LayoutViewState(private val resource: Resource<*>) {

    fun isLoading() = resource is Resource.Loading

    fun isSuccess() = resource is Resource.Success

    fun isFailed() = resource is Resource.Failed
}
