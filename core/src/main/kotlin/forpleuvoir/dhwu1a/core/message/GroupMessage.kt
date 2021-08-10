package forpleuvoir.dhwu1a.core.message

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.message.base.Message
import forpleuvoir.dhwu1a.core.message.base.MessageType
import forpleuvoir.dhwu1a.core.message.messagesender.GroupMessageSender
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message
 *
 * #class_name GroupMessage
 *
 * #create_time 2021/7/1 20:51
 */
class GroupMessage(`object`: JsonObject) : Message(MessageType.GroupMessage, `object`) {
    /**
     * 相关的群
     */
    @JvmField
    val group: Group = sender.user as Group

    /**
     * 发送消息的群员
     */
    var member: Member? = (sender as GroupMessageSender).member

    override fun printMessageLog() {
        log.info(
            "RM/Bot.{}:[{}({})] {}({}) >> {}", bot.id, group.data.name, group.id,
            member!!.data.memberName,
            member!!.id, this.toPlainText()
        )
    }

}