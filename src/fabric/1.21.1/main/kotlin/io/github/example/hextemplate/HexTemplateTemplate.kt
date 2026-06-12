package io.github.example.hextemplate

import net.fabricmc.api.ModInitializer

object FabricHexTemplate : ModInitializer {
    override fun onInitialize() {
        HexTemplate.init()
    }
}
