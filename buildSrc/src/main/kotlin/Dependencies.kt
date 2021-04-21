
object Dependencies {

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val fragmentKTX = "androidx.fragment:fragment-ktx:${Versions.fragmentKTX}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val lifecycleEXT = "android.arch.lifecycle:extensions:${Versions.lifecycle}"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val kodein = "org.kodein.di:kodein-di-generic-jvm:${Versions.kodein}"
        const val kodeinAndroidX = "org.kodein.di:kodein-di-framework-android-x:${Versions.kodein}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val baseRecycler = "com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.50"
        const val materialDialogs ="com.afollestad.material-dialogs:core:3.3.0"
    }
}