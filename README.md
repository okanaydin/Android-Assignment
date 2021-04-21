# Storytel Android Assignment

## :scroll: Description
This assignment created for Storytel that gives you basically a post list and its detail with comments. 

## :iphone: Features

- [x] Users can see random post list.
- [x] Users can see the comments of the post.

## :camera_flash: Screenshots

Splash Screen             |  Post List Screen |  Post Detail Screen
:-------------------------:|:-------------------------:|:-------------------------:
<img src="/results/screenshot_1.png" width="260">| <img src="/results/screenshot_2.png" width="260">|<img src="/results/screenshot_5.png" width="260">  | 
Error Screen             |  Missing Data Screen |
<img src="/results/screenshot_4.png" width="260">| <img src="/results/screenshot_3.png" width="260">

## :rocket: What the project uses ?
* [Architecture Components](https://developer.android.com/topic/libraries/architecture/)
* [Android X](https://developer.android.com/jetpack/androidx) 
* [Android KTX](https://developer.android.com/kotlin/ktx.html) 
* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit2](https://square.github.io/retrofit/)
* [OkHttp3](https://github.com/square/okhttp)
* [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
* [Kotlin Flow](https://developer.android.com/kotlin/flow)
* [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [Moshi](https://github.com/square/moshi)
* [Coil](https://github.com/coil-kt/coil)
* [ktlint](https://ktlint.github.io/)

## :file_folder: Package Structure
    
    app.storytel.candidate.com    # root package
    .
    ├── core
    │   └── resource              # resource handling
    |
    ├── data
    │   ── remote                 # api calls
    │     └── models	      # response model
    │     └── remote data source  # data source
    |
    ├── features
    │   ── feature1               # post list
    │     └── di                  # dependency Injection
    │     └── repository          # repository
    │     └── ui                  # fragments, viewModel, viewState, adapters
    │     └── usecase             # use case
    .

## :writing_hand: GitHub Projects
I have created issues for the defined requirements such as features, improvements, and bugs to assign myself and track those issues on the board. Basically, there are three columns which are <b>In-Progress, To-Do, </b> and <b>Done.</b>
<img src="/results/board.png">

## :running_man: GitHub Action
I have implemented GitHub Action for this project and when you create a new pull request it goes to build the project automatically if there is a problem in your build time you can easily see the issue.
<img src="/results/action.png">

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
