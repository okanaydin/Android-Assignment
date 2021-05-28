package com.okanaydin.assignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @HiltAndroidApp triggers Hilt's code generation, including a base class for your application that serves as the application-level dependency container.
 * ref: https://developer.android.com/training/dependency-injection/hilt-android#application-class
 */
@HiltAndroidApp
class Application : Application()
