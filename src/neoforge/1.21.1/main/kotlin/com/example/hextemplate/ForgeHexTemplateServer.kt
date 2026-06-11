package com.example.hextemplate

import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object ForgeHexTemplateServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        HexTemplate.initServer()
    }
}

