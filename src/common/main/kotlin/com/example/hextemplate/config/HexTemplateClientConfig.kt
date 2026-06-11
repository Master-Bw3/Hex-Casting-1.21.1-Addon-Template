package com.example.hextemplate.config

import com.example.hextemplate.HexTemplate
import me.fzzyhmstrs.fzzy_config.config.Config

// guide: https://moddedmc.wiki/en/project/fzzy-config/latest/docs/config-design/New-Configs#2-config-creation
class HexTemplateClientConfig : Config(HexTemplate.id("client_config")) {

    var testValue = 2.0

}