package app.storytel.candidate.com.features.details.di

import app.storytel.candidate.com.data.remote.api.PostService
import app.storytel.candidate.com.data.remote.datasource.posts.PostDataSource
import app.storytel.candidate.com.data.remote.datasource.posts.PostDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommentModule {

    @Provides
    @Singleton
    fun providePostDataSource(postService: PostService): PostDataSource {
        return PostDataSourceImp(postService)
    }
}
