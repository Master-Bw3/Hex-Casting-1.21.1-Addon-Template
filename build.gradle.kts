import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import at.petrak.pkpcpbp.filters.FlatteningJson5Transmogrifier
import at.petrak.pkpcpbp.filters.Json5Transmogrifier

plugins {
    alias(libs.plugins.cloche)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.yamlang)
    alias(libs.plugins.pkJson5)
}

val modVersion: String by project
val mavenGroup: String by project

version = modVersion
group = mavenGroup

kotlin {
    jvmToolchain(21)
}


repositories {
    mavenLocal()
    mavenCentral()

    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }

    maven("https://thedarkcolour.github.io/KotlinForForge")

    // Hex Casting, Paucal
    maven("https://maven.blamejared.com")

    // Cardinal Components
    maven("https://maven.ladysnake.org/releases")

    // Cloth Config
    maven("https://maven.shedaniel.me")

    // Mod Menu
    maven("https://maven.terraformersmc.com/releases")

    // Forgified Fabric Api
    maven("https://maven.su5ed.dev/releases")

    // Caelus
    maven("https://maven.theillusivec4.top/")

    // Accessories, oωo Lib
    maven("https://maven.wispforest.io/releases")

    maven("https://jitpack.io")

    // Fzzy Config
    maven("https://maven.fzzyhmstrs.me/")

    // for deps that are not hosted on any maven repository
    flatDir { dir(rootProject.file("libs")) }

    exclusiveContent {
        filter {
            includeGroup("maven.modrinth")
        }
        forRepository {
            maven { url = uri("https://api.modrinth.com/maven") }
        }
    }
}

cloche {
    metadata {
        modId = "hextemplate"
        name = "Hex Template"
        description = "Put a description here"
        license = "MIT"

        author("Me")
    }

    common {
        accessWideners.from(file("src/common/hextemplate.accesswidener"))
        mixins.from(file("src/common/hextemplate.mixins.json"))

        mappings {
            official()
            parchment("2024.11.17")
        }

        dependencies {
            // the parts of mods that are the same in all sourceSets
            // will automatically be available in the common sourceSet
        }
    }

    fabric("fabric:1.21.1") {
        loaderVersion = libs.versions.fabric.loader
        minecraftVersion = libs.versions.minecraft

        mixins.from(file("src/fabric/1.21.1/hextemplate-fabric.mixins.json"))
        datagenDirectory = file("src/common/main/generated")
        includedClient()
        data()

        mappings {
            official()
            parchment("2024.11.17")
        }

        dependencies {
            fabricApi(libs.versions.fabric.api)
            modImplementation(libs.kotlin.fabric)

            // hex casting and dependencies
            libs.bundles.hexcasting.fabric.get().forEach {
                modImplementation(it)
            }
            libs.bundles.cardinalComponents.get().forEach {
                modImplementation(it)
            }
            modRuntimeOnly(libs.architectury.fabric)

            modImplementation(libs.modMenu)
            modImplementation(libs.owoLib.fabric)
            modImplementation(libs.fzzyConfig.fabric)
        }

        metadata {
            entrypoint("main") {
                value = "com.example.hextemplate.FabricHexTemplate"
                adapter = "kotlin"
            }
            entrypoint("client") {
                value = "com.example.hextemplate.client.FabricHexTemplateClient"
                adapter = "kotlin"
            }
            entrypoint("fabric-datagen") {
                value = "com.example.hextemplate.datagen.FabricHexTemplateDatagen"
                adapter = "kotlin"
            }
        }

        runs {
            client {
                jvmArguments.add("-XX:+AllowEnhancedClassRedefinition")
            }
            server()
            data()
        }
    }

    neoforge("neoforge:1.21.1") {
        loaderVersion = libs.versions.neoforge
        minecraftVersion = libs.versions.minecraft

        mixins.from(file("src/neoforge/1.21.1/hextemplate-neoforge.mixins.json"))
        datagenDirectory = file("src/common/main/generated")
        data()

        mappings {
            official()
            parchment("2024.11.17")
        }

        dependencies {
            modImplementation(libs.forgifiedFabricApi)
            modImplementation(libs.kotlin.forge)

            // hex casting and dependencies
            libs.bundles.hexcasting.neoforge.get().forEach {
                modImplementation(it)
            }
            modRuntimeOnly(libs.architectury.neoforge)

            modImplementation(libs.owoLib.neoforge)
            modImplementation(libs.fzzyConfig.neoforge)
        }

        runs {
            client {
                jvmArguments.add("-XX:+AllowEnhancedClassRedefinition")
            }
            server()
            data()
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        languageVersion = KotlinVersion.KOTLIN_2_2
        freeCompilerArgs.addAll(
            "-Xmulti-platform",
            "-Xno-check-actual",
            "-Xexpect-actual-classes",
        )
    }
}


// yaml language files
// https://github.com/Fallen-Breath/yamlang/tree/master
yamlang {
    targetSourceSets = listOf(sourceSets["fabric1211"], sourceSets["neoforge1211"])
    inputDir = "assets/hextemplate/lang"
    owolibRichTranslations = true
}

// https://github.com/gamma-delta/PKPCPBP/blob/786194a590/src/main/java/at/petrak/pkpcpbp/PKSubprojPlugin.java#L84
tasks.withType<ProcessResources>().configureEach {
    outputs.upToDateWhen { false }

    filesMatching(listOf("assets/**/*.flatten.json5", "data/**/*.flatten.json5")) {
        path = path.replace(".flatten.json5", ".json")
        filter<FlatteningJson5Transmogrifier>()
    }

    filesMatching(listOf("assets/**/*.json5", "data/**/*.json5")) {
        path = path.replace(".json5", ".json")
        filter<Json5Transmogrifier>()
    }
}


// workaround for https://github.com/terrarium-earth/cloche/issues/150
tasks.named("runFabric1211Data") {
    enabled = false
}
tasks.named("prepareFabric1211DataRun") {
    dependsOn(tasks.named("runNeoforge1211Data"))
}

// disable the default compile tasks so the build task works properly
// (cloche doesn't use them to build the jars)
tasks.named("compileKotlin") {
    enabled = false
}
tasks.named("compileJava") {
    enabled = false
}
