package com.example.hextemplate.networking

import com.example.hextemplate.HexTemplate
import com.example.hextemplate.networking.msg.HexTemplateMessageCompanion
import io.wispforest.owo.network.OwoNetChannel

object HexTemplateNetworking {
    val CHANNEL: OwoNetChannel = OwoNetChannel.create(HexTemplate.id("networking_channel"))

    fun init() {
        for (subclass in HexTemplateMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
