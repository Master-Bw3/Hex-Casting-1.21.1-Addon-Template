package com.example.hextemplate.client

import com.example.hextemplate.client.HexTemplateClient
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT

object NeoForgeHexTemplateClient {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLClientSetupEvent) {
        HexTemplateClient.init()
//        LOADING_CONTEXT.registerExtensionPoint(IConfigScreenFactory::class.java) {
//            IConfigScreenFactory { _, parent -> HexTemplateClient.getConfigScreen(parent) }
//        }
    }
}