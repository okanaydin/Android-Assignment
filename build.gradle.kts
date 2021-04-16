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
        classpath(ClassPaths.gradle)
        classpath(ClassPaths.kotlinGradlePlugin)
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
