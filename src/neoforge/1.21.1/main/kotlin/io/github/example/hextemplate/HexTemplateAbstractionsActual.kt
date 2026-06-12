@file:JvmName("HexTemplateAbstractionsActual")

package io.github.example.hextemplate

import io.github.example.hextemplate.registry.HexTemplateRegistrar
import net.msrandom.multiplatform.annotations.Actual
import net.neoforged.neoforge.registries.RegisterEvent

actual fun <T : Any> initRegistry(registrar: HexTemplateRegistrar<T>) {
        NeoForgeHexTemplate.container.eventBus!!.addListener { event: RegisterEvent ->
            event.register(registrar.registryKey) { helper ->
                registrar.init(helper::register)
            }
        }
}