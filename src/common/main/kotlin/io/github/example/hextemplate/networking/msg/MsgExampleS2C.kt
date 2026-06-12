package io.github.example.hextemplate.networking.msg

@JvmRecord
data class MsgExampleS2C(val payload: Int) : HexTemplateMessageS2C {
   companion object : HexTemplateMessageCompanion<MsgExampleS2C> {
       override val type = MsgExampleS2C::class.java
   }
}
