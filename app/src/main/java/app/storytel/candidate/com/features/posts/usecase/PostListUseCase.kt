package app.storytel.candidate.com.features.posts.usecase

import app.storytel.candidate.com.features.core.Resource
import app.storytel.candidate.com.data.remote.datasource.model.PhotoModel
import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel
import app.storytel.candidate.com.data.remote.datasource.model.PostModel
import app.storytel.candidate.com.features.posts.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostListUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    /**
     * In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
     * ref: https://developer.android.com/kotlin/flow
     */
    fun getCombinedPostsAndPhotos() = flow {
        emit(Resource.loading())
        emit(Resource.success(getPostsAndPhotos()))
    }.catch { exception ->
        emit(Resource.failed(exception))
    }.flowOn(Dispatchers.IO)

    /**
     *  In parallel, fetch posts and photos and return when both requests
     *  ref: https://developer.android.com/kotlin/coroutines/coroutines-best-practices#create-coroutines-data-layer
     */
    private suspend fun getPostsAndPhotos() = coroutineScope {

        val deferredPhotos = async { postRepository.getPhotos() }
        val photos = try {
            deferredPhotos.await()
        } catch (e: Exception) {
            listOf()
        }

        val deferredPosts = async { postRepository.getPosts() }
        val post = try {
            deferredPosts.await()
        } catch (e: Exception) {
            listOf()
        }
        combinePostsAndPhotos(post, photos)
    }

    private fun combinePostsAndPhotos(
        postList: List<PostModel>,
        photoList: List<PhotoModel>
    ): List<PostAndPhotoModel> {
        var selectedPhoto: PhotoModel?
        return postList.map { postResponse ->
            selectedPhoto = if (photoList.isEmpty()) {
                null
            } else {
                photoList.random()
            }
            PostAndPhotoModel(
                postItem = postResponse,
                thumbnailUrl = selectedPhoto?.thumbnailUrl,
                imageUrl = selectedPhoto?.url
            )
        }
    }
}
