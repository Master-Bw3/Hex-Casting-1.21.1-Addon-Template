@file:JvmName("HexTemplateAbstractionsActual")

package com.example.hextemplate

import com.example.hextemplate.registry.HexTemplateRegistrar
import net.msrandom.multiplatform.annotations.Actual

actual fun <T : Any> initRegistry(registrar: HexTemplateRegistrar<T>) {
//        MOD_BUS.addListener { event: RegisterEvent ->
//            event.register(registrar.registryKey) { helper ->
//                registrar.init(helper::register)
//            }
//        }
}