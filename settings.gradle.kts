pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // ✅ This line is CRUCIAL — it must allow module-level repositories.
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "C2C_v2"

// ✅ Include all your modules:
include(":app")
include(":domain")
include(":data")
include(":core-common")
include(":feature-auth")
include(":feature-vehicle")
include(":feature-map")
include(":feature-alerts")
include(":feature-esp32")
