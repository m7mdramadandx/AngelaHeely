object TestLibs {

    const val junit = "junit:junit:4.13"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.ARCH_CORE_TESTING_VER}"
    const val testRunner = "androidx.test:runner:${Versions.TEST_RUNNER_VER}"
    const val rules = "androidx.test:rules:${Versions.RULES_VER}"
    const val truth = "androidx.test.ext:truth:${Versions.TRUTH_VER}"
    const val junitExt = "androidx.test.ext:junit-ktx:${Versions.JUNIT_EXT_VER}"
    const val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.MOCKITO}"
    const val espressoContrib =
        "com.android.support.test.espresso:espresso-contrib:${Versions.ESPRESSO}"

    // For instrumentation tests
    const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.HILT}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.MOCK_WEB_SERVER}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

}
