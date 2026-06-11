package com.example.hextemplate

import com.example.hextemplate.client.NeoForgeHexTemplateClient
import com.example.hextemplate.datagen.ForgeHexTemplateDatagen
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.ModList
import net.neoforged.fml.common.Mod

@Mod(HexTemplate.MODID)
class NeoForgeHexTemplate(modBus: IEventBus, container: ModContainer) {
    init {
        modBus.apply {
            addListener(NeoForgeHexTemplateClient::init)
            addListener(ForgeHexTemplateDatagen::init)
            addListener(ForgeHexTemplateServer::init)
        }
        HexTemplate.init()
    }

    companion object {
        internal val container: ModContainer
            get() = ModList.get().getModContainerById(HexTemplate.MODID).get()
    }
}
