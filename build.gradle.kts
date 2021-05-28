buildscript {
    /**
     * Bintray's JCenter repository for dependencies is deprecated.
     * JFrog announced JCenter's shutdown in February 2021. Use mavenCentral() instead.
     * ref: https://blog.gradle.org/jcenter-shutdown
     */
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(ClassPaths.daggerHilt)
        classpath(ClassPaths.gradle)
        classpath(ClassPaths.kotlinGradlePlugin)
        classpath(ClassPaths.navigationSafeArgPlugin)
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.7.1.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
