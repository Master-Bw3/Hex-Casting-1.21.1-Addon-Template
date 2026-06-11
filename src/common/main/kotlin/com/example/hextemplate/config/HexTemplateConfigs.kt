package com.example.hextemplate.config

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.api.RegisterType

object HexTemplateConfigs {
    //instance of your config loaded from file and automatically registered to the SyncedConfigRegistry and ClientConfigRegistry using the getId() method
    var commonConfig = ConfigApi.registerAndLoadConfig(::HexTemplateClientConfig)

    //adding the registerType, you can register a config as client-only. No syncing will occur. Useful for client-only mods.
    var clientConfig = ConfigApi.registerAndLoadConfig(::HexTemplateCommonConfig, RegisterType.CLIENT)

    fun init() {}
}