package io.github.example.hextemplate.networking.msg

import io.github.example.hextemplate.HexTemplate
import io.github.example.hextemplate.networking.HexTemplateNetworking
import io.github.example.hextemplate.networking.handler.applyOnClient
import io.github.example.hextemplate.networking.handler.applyOnServer
import io.wispforest.owo.network.ClientAccess
import io.wispforest.owo.network.OwoNetChannel
import io.wispforest.owo.network.ServerAccess
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer

sealed interface HexTemplateMessage

sealed interface HexTemplateMessageC2S : HexTemplateMessage {
    fun <T> T.sendToServer() where T : Record {
        HexTemplateNetworking.CHANNEL.clientHandle().send(this)
    }
}

sealed interface HexTemplateMessageS2C : HexTemplateMessage {
}

fun <T> T.sendToPlayer(player: ServerPlayer) where T : Record {
    HexTemplateNetworking.CHANNEL.serverHandle(player).send( this)
}

fun <T> T.sendToPlayers(players: Iterable<ServerPlayer>) where T : Record {
    players.forEach { sendToPlayer(it) }
}

sealed interface HexTemplateMessageCompanion<T> where T : HexTemplateMessage, T : Record {
    val type: Class<T>

    fun apply(msg: T, access: ServerAccess): Unit {
        HexTemplate.LOGGER.debug("Server received packet from {}: {}", access.player().name.string, this)
        when (msg) {
            is HexTemplateMessageC2S -> msg.applyOnServer(access)
            else -> HexTemplate.LOGGER.warn("Message not handled on server: {}", msg::class)
        }
    }

    fun apply(msg: T, access: ClientAccess): Unit {
        HexTemplate.LOGGER.debug("Client received packet: {}", this)
        when (msg) {
            is HexTemplateMessageS2C -> msg.applyOnClient(access)
            else -> HexTemplate.LOGGER.warn("Message not handled on client: {}", msg::class)
        }
    }

    fun register(channel: OwoNetChannel) {
        channel.registerServerbound(type) { msg, access -> apply(msg, access) }
    }
}
