plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "ispy"
include("lib")

// Enable Gradle Build Cache
buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, "build-cache")
    }
}
