package com.okanaydin.assignment.features.posts.di

import com.okanaydin.assignment.data.remote.api.PhotoService
import com.okanaydin.assignment.data.remote.api.PostService
import com.okanaydin.assignment.data.remote.datasource.photos.PhotoRemoteDataSource
import com.okanaydin.assignment.data.remote.datasource.photos.PhotoRemoteDataSourceImp
import com.okanaydin.assignment.data.remote.datasource.posts.PostRemoteDataSource
import com.okanaydin.assignment.data.remote.datasource.posts.PostRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * A Hilt module is a class that is annotated with @Module. Like a Dagger module, it informs Hilt how to provide instances of certain types.
 * Unlike Dagger modules, you must annotate Hilt modules with @InstallIn to tell Hilt which Android class each module will be used or installed in.
 * ref: https://developer.android.com/training/dependency-injection/hilt-android#hilt-modules
 */

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    /**
     * @Provides annotation would be accessed across the application.
     * @Singleton annotation helps the instance to be created and used once across the application.
     */
    @Provides
    @Singleton
    fun providePostDataSource(postService: PostService): PostRemoteDataSource {
        return PostRemoteDataSourceImp(postService)
    }

    @Provides
    @Singleton
    fun providePhotoDataSource(photoService: PhotoService): PhotoRemoteDataSource {
        return PhotoRemoteDataSourceImp(photoService)
    }
}
