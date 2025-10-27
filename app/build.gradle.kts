plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.c2c.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.c2c.app"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures { compose = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

repositories {
    // ✅ Add this block explicitly
    google()
    mavenCentral()
}

dependencies {
    implementation(project(":feature-auth"))
    implementation(project(":feature-vehicle"))
    implementation(project(":feature-map"))
    implementation(project(":feature-alerts"))
    implementation(project(":feature-esp32"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core-common"))

    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.compose.ui:ui:1.9.3")
    implementation("androidx.compose.material3:material3:1.4.0")
    implementation("androidx.compose.material:material-icons-extended:1.7.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.9.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.9.3")
    implementation("androidx.navigation:navigation-compose:2.9.5")

    implementation("com.google.android.gms:play-services-maps:19.2.0")

    // ✅ Firebase (BoM)
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
}
