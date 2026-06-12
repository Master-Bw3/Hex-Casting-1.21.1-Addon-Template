package io.github.example.hextemplate.client

import io.github.example.hextemplate.client.HexTemplateClient
import net.fabricmc.api.ClientModInitializer

object FabricHexTemplateClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexTemplateClient.init()
    }
}