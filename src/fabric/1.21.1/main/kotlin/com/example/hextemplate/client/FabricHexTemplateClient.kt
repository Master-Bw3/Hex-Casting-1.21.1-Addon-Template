package com.example.hextemplate.client

import com.example.hextemplate.client.HexTemplateClient
import net.fabricmc.api.ClientModInitializer

object FabricHexTemplateClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexTemplateClient.init()
    }
}