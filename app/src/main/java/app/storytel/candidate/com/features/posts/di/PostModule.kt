package app.storytel.candidate.com.features.posts.di

import app.storytel.candidate.com.data.remote.api.PostService
import app.storytel.candidate.com.data.remote.datasource.posts.PostDataSource
import app.storytel.candidate.com.data.remote.datasource.posts.PostDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

/**
 * A Hilt module is a class that is annotated with @Module. Like a Dagger module, it informs Hilt how to provide instances of certain types.
 * Unlike Dagger modules, you must annotate Hilt modules with @InstallIn to tell Hilt which Android class each module will be used or installed in.
 * ref: https://developer.android.com/training/dependency-injection/hilt-android#hilt-modules
 */

@Module
@InstallIn(ViewModelComponent::class)
object PostModule {

    /**
     * @Provides annotation would be accessed across the application.
     * @Singleton annotation helps the instance to be created and used once across the application.
     */
    @Provides
    @Singleton
    fun providePostDataSource(postService: PostService): PostDataSource {
        return PostDataSourceImp(postService)
    }
}
