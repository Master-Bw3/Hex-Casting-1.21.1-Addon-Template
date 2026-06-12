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
                // Use Master-Bw3's fork of Auri's fork of yamlang that allows for multiple directories to be transformed
                "me.fallenbreath.yamlang" -> {
                    useModule("com.github.Master-Bw3:yamlang:${requested.version}")
                }
            }
        }
    }
}