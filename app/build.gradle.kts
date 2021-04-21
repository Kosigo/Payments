plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.android.extensions")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion(Config.buildToolsVersion)

    defaultConfig {
        applicationId = "com.kosigo.payments"
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Config.androidTestInstrumentation

        buildConfigFieldFromGradleProperty("apiBaseUrl")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.fragmentKTX)
    implementation(Dependencies.AndroidX.lifecycleEXT)
    implementation(Dependencies.AndroidX.lifecycleLiveData)
    implementation(Dependencies.AndroidX.lifecycleViewModel)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.kodein)
    implementation(Dependencies.AndroidX.kodeinAndroidX)
    implementation(Dependencies.AndroidX.okHttp)
    implementation(Dependencies.AndroidX.logging)
    implementation(Dependencies.AndroidX.retrofit)
    implementation(Dependencies.AndroidX.retrofitMoshi)
    implementation(Dependencies.AndroidX.baseRecycler)
    implementation(Dependencies.AndroidX.materialDialogs)
}

fun com.android.build.gradle.internal.dsl.BaseFlavor.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as String
    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }

