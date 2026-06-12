package io.github.example.hextemplate

import io.github.example.hextemplate.client.NeoForgeHexTemplateClient
import io.github.example.hextemplate.datagen.NeoForgeHexTemplateDatagen
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.ModList
import net.neoforged.fml.common.Mod

@Mod(HexTemplate.MODID)
class NeoForgeHexTemplate(modBus: IEventBus, container: ModContainer) {
    init {
        modBus.apply {
            addListener(NeoForgeHexTemplateClient::init)
            addListener(NeoForgeHexTemplateDatagen::init)
            addListener(NeoForgeHexTemplateServer::init)
        }
        HexTemplate.init()
    }

    companion object {
        internal val container: ModContainer
            get() = ModList.get().getModContainerById(HexTemplate.MODID).get()
    }
}
