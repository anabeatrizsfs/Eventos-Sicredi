// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(AppDependencies.gradle)
        classpath(AppDependencies.kotlinPlugin)
        classpath(AppDependencies.googleServices)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = java.net.URI.create("https://jitpack.io") }
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
