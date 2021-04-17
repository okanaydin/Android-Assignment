package app.storytel.candidate.com.core

class LayoutViewState(private val resource: Resource<*>) {

    fun isLoading() = resource is Resource.Loading

    fun isSuccess() = resource is Resource.Success

    fun isFailed() = if (resource is Resource.Failed) resource.message else "Please try again later"
}