buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(GradlePlugins.Android)
        classpath(GradlePlugins.Kotlin)
    }
}

allprojects {
    repositories {
        //google()
        jcenter()
        maven { url = uri("https://www.jitpack.io") }
        maven { url = uri("https://maven.google.com") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}