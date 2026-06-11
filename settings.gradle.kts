dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.msrandom.net/repository/cloche/")
        maven("https://maven.blamejared.com/")
        maven("https://jitpack.io")
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "me.fallenbreath.yamlang" -> {
                    useModule("com.github.Master-Bw3:yamlang:${requested.version}")
                }
            }
        }
    }
}