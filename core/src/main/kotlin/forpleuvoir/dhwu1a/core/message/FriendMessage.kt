package forpleuvoir.dhwu1a.core.message

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.FRIEND
import forpleuvoir.dhwu1a.core.message.base.Message
import forpleuvoir.dhwu1a.core.message.base.MessageType
import forpleuvoir.dhwu1a.core.user.Friend

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message
 *
 * #class_name FriendMessage
 *
 * #create_time 2021/7/1 1:29
 */
class FriendMessage(`object`: JsonObject) : Message(MessageType.FriendMessage, `object`) {
    /**
     * 发送消息的好友
     */
    @JvmField
    @SerializedName(FRIEND)
    val friend: Friend? = sender.user as Friend?
    override fun printMessageLog() {
        log.info("RM/Bot.{}:{}({}) >> {}", bot.id, friend?.data?.nickname, friend?.id, this.toPlainText())
    }

}