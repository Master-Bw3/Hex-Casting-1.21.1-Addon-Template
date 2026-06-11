package com.example.hextemplate.networking.msg

import net.minecraft.network.FriendlyByteBuf

//@JvmRecord
//data class MsgSyncConfigS2C(val serverConfig: HexTemplateServerConfig.ServerConfig) : HexTemplateMessageS2C {
//    companion object : HexTemplateMessageCompanion<MsgSyncConfigS2C> {
//        override val type = MsgSyncConfigS2C::class.java
//
//        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
//            serverConfig = HexTemplateServerConfig.ServerConfig().decode(buf),
//        )
//
//        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
//            serverConfig.encode(buf)
//        }
//    }
//}
