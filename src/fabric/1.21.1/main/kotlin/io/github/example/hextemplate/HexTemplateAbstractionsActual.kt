@file:JvmName("HexTemplateAbstractionsActual")

package io.github.example.hextemplate

import io.github.example.hextemplate.registry.HexTemplateRegistrar
import net.minecraft.core.Registry
import net.msrandom.multiplatform.annotations.Actual

actual fun <T : Any> initRegistry(registrar: HexTemplateRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
