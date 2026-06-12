package io.github.example.hextemplate

import net.fabricmc.api.DedicatedServerModInitializer

object FabricHexTemplateServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        HexTemplate.initServer()
    }
}
