import java.util.Properties

plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
    alias(libs.plugins.android.application)
}

val props = Properties().apply {
    rootProject.file("local.properties").inputStream().use { load(it) }
}
val url = props.getProperty("SUPABASE_URL")
    ?: error("Falta SUPABASE_URL en local.properties")
val key = props.getProperty("SUPABASE_KEY")
    ?: error("Falta SUPABASE_KEY en local.properties")

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.uteq.software.appsupabasesoftw2026"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.uteq.software.appsupabasesoftw2026"
        minSdk = 34
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "SUPABASE_URL", "\"$url\"")
        buildConfigField("String", "SUPABASE_KEY", "\"$key\"")
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(platform("io.github.jan-tennert.supabase:bom:3.6.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.ktor:ktor-client-android:3.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

}