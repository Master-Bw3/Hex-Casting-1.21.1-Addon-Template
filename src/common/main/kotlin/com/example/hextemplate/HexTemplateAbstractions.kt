@file:JvmName("HexTemplateAbstractions")

package com.example.hextemplate

import com.example.hextemplate.registry.HexTemplateRegistrar

fun initRegistries(vararg registries: HexTemplateRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

expect fun <T : Any> initRegistry(registrar: HexTemplateRegistrar<T>)
