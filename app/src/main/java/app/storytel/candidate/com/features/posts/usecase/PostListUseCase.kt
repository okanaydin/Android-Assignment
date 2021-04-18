package app.storytel.candidate.com.features.posts.usecase

import app.storytel.candidate.com.core.Resource
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

    fun getCombinedPostsAndPhotos() = flow {
        emit(Resource.loading())
        emit(Resource.success(getPostsAndPhotos()))
    }.catch { exception ->
        emit(Resource.failed(exception.message.toString()))
    }.flowOn(Dispatchers.IO)

    /**
     *  In parallel, fetch posts and photos and return when both requests
     *  ref: https://developer.android.com/kotlin/coroutines/coroutines-best-practices#create-coroutines-data-layer
     */
    private suspend fun getPostsAndPhotos() = coroutineScope {

        val posts = async { postRepository.getPosts() }
        val photos = async { postRepository.getPhotos() }
        combinePostsAndPhotos(posts.await(), photos.await())
    }

    private fun combinePostsAndPhotos(
        postList: List<PostModel>,
        photoList: List<PhotoModel>
    ): List<PostAndPhotoModel> {

        return postList.map { postResponse ->
            val selectedPhoto = photoList.random()
            PostAndPhotoModel(
                postItem = postResponse,
                thumbnailUrl = selectedPhoto.thumbnailUrl,
                imageUrl = selectedPhoto.url
            )
        }
    }
}
