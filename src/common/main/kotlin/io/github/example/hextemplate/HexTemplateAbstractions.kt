@file:JvmName("HexTemplateAbstractions")

package io.github.example.hextemplate

import io.github.example.hextemplate.registry.HexTemplateRegistrar

fun initRegistries(vararg registries: HexTemplateRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

expect fun <T : Any> initRegistry(registrar: HexTemplateRegistrar<T>)
