import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coreSplashscreen = "androidx.core:core-splashscreen:${Versions.coreSplashscreen}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"

    const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.coroutinesLifecycleScope}"
    const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.coroutinesLifecycleScope}"
    const val lifeCycleRuntimeCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.coroutinesLifecycleScope}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"

    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"

    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial = "androidx.compose.material3:material3"

    const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val hiltComposeNavigation =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"

    const val junit = "junit:junit:${Versions.junit}"

    const val androidxJunit = "androidx.test.ext:junit:${Versions.testJunit}"
    const val androidxEspresso = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4"

    const val toolingUi = "androidx.compose.ui:ui-tooling"
    const val manifestTest = "androidx.compose.ui:ui-test-manifest"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    const val googleAccompanistSwiperefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.swipeRefresh}"
    const val googleAccompanistSystemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}"

    const val androidyoutubeplayer = "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.androidyoutubeplayer}"
}

fun DependencyHandler.core() {
    implementation (Dependencies.coreSplashscreen)
    implementation(Dependencies.core)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.androidMaterial)
}

fun DependencyHandler.coroutinesLifecycleScope() {
    implementation(Dependencies.lifeCycleViewModel)
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.lifeCycleRuntimeCompose)
}

fun DependencyHandler.googleAccompanist() {
    implementation(Dependencies.googleAccompanistSwiperefresh)
    implementation(Dependencies.googleAccompanistSystemuicontroller)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeActivity)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.navigation)
    implementation(Dependencies.hiltComposeNavigation)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.coil() {
    implementation(Dependencies.coil)
}

fun DependencyHandler.androidyoutubeplayer() {
    implementation(Dependencies.androidyoutubeplayer)
}

fun DependencyHandler.test() {
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidxJunit)
    androidTestImplementation(Dependencies.androidxEspresso)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTest)

    debugImplementation(Dependencies.toolingUi)
    debugImplementation(Dependencies.manifestTest)
}

fun DependencyHandler.data() {
    implementation(project(":data"))
}

fun DependencyHandler.domain() {
    implementation(project(":domain"))
}


fun DependencyHandler.presentation() {
    implementation(project(":presentation"))
}



