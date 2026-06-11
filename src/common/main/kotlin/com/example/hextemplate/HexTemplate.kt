package com.example.hextemplate

import com.example.hextemplate.config.HexTemplateConfigs
import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import com.example.hextemplate.networking.HexTemplateNetworking
import com.example.hextemplate.registry.HexTemplateActions

object HexTemplate {
    const val MODID = "hextemplate"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)



    @JvmStatic
    fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, path)

    fun init() {
        initRegistries(
            HexTemplateActions,
        )
        HexTemplateNetworking.init()
        HexTemplateConfigs.init()
    }

    fun initServer() {
    }
}
