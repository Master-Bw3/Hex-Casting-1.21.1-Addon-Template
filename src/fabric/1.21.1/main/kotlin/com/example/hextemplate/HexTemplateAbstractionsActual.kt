@file:JvmName("HexTemplateAbstractionsActual")

package com.example.hextemplate

import com.example.hextemplate.registry.HexTemplateRegistrar
import net.minecraft.core.Registry
import net.msrandom.multiplatform.annotations.Actual

actual fun <T : Any> initRegistry(registrar: HexTemplateRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
