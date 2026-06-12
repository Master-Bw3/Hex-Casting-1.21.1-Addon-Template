package io.github.example.hextemplate

import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object NeoForgeHexTemplateServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        HexTemplate.initServer()
    }
}

