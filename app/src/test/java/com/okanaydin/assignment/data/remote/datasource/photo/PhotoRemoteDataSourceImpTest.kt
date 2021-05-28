package com.okanaydin.assignment.data.remote.datasource.photo

import com.okanaydin.assignment.data.remote.api.PhotoService
import com.okanaydin.assignment.data.remote.datasource.model.PhotoModelFactory.getPhoto
import com.okanaydin.assignment.data.remote.datasource.photos.PhotoRemoteDataSourceImp
import com.okanaydin.assignment.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotoRemoteDataSourceImpTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var photoService: PhotoService

    private lateinit var photoRemoteDataSourceImp: PhotoRemoteDataSourceImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        photoRemoteDataSourceImp = PhotoRemoteDataSourceImp(photoService)
    }

    @Test
    fun `check getPhotos()`() = runBlocking {
        // given
        coEvery { photoService.fetchPhotos() } returns listOf(getPhoto())

        // when
        val result = photoRemoteDataSourceImp.getPhotos()

        // then
        coVerify { photoService.fetchPhotos() }
        assertEquals(result, listOf(getPhoto()))
    }
}
