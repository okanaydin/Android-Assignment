package com.okanaydin.assignment.data.di

import com.okanaydin.assignment.com.BuildConfig
import com.okanaydin.assignment.data.remote.api.PhotoService
import com.okanaydin.assignment.data.remote.api.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * A Hilt module is a class that is annotated with @Module. Like a Dagger module, it informs Hilt how to provide instances of certain types.
 * Unlike Dagger modules, you must annotate Hilt modules with @InstallIn to tell Hilt which Android class each module will be used or installed in.
 * ref: https://developer.android.com/training/dependency-injection/hilt-android#hilt-modules
 */

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    private const val BASE_API_URL = "https://jsonplaceholder.typicode.com/"

    /**
     * @Provides annotation would be accessed across the application.
     * @Singleton annotation helps the instance to be created and used once across the application.
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                // development build
                HttpLoggingInterceptor.Level.BODY
            } else {
                // production build
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_API_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun providePhotoService(retrofit: Retrofit): PhotoService {
        return retrofit.create()
    }
}
