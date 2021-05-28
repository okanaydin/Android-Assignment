package com.okanaydin.assignment.features.core

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LayoutViewStateTest {

    private lateinit var layoutViewState: LayoutViewState

    @ParameterizedTest(name = "when state is {0}, then isLoading returns {1}")
    @MethodSource("isLoadingProvider")
    fun `check isLoading`(resource: Resource<*>, expected: Boolean) {
        // given
        layoutViewState = LayoutViewState(resource)

        // when
        val result = layoutViewState.isLoading()

        // then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("isSuccessProvider")
    fun `check isSuccess`(resource: Resource<*>, expected: Boolean) {
        // given
        layoutViewState = LayoutViewState(resource)

        // when
        val result = layoutViewState.isSuccess()

        // then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("isFailedProvider")
    fun `check isFailed`(resource: Resource<*>, expected: Boolean) {
        // given
        layoutViewState = LayoutViewState(resource)

        // when
        val result = layoutViewState.isFailed()

        // then
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun isLoadingProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Resource.Loading<Unit>(), true),
                Arguments.of(Resource.Success(Any()), false),
                Arguments.of(Resource.Failed<Unit>(Throwable()), false)
            )
        }

        @JvmStatic
        fun isSuccessProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Resource.Loading<Unit>(), false),
                Arguments.of(Resource.Success(Any()), true),
                Arguments.of(Resource.Failed<Unit>(Throwable()), false)
            )
        }

        @JvmStatic
        fun isFailedProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Resource.Loading<Unit>(), false),
                Arguments.of(Resource.Success(Any()), false),
                Arguments.of(Resource.Failed<Unit>(Throwable()), true)
            )
        }
    }
}
