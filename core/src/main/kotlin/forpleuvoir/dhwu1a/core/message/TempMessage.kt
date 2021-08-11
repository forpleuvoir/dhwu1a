package forpleuvoir.dhwu1a.core.message

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.message.base.Message
import forpleuvoir.dhwu1a.core.message.base.MessageType
import forpleuvoir.dhwu1a.core.message.messagesender.TempMessageSender
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message
 *
 * #class_name TempMessage
 *
 * #create_time 2021/7/1 21:37
 */
class TempMessage(`object`: JsonObject) : Message(MessageType.TempMessage, `object`) {
    /**
     * 发送消息的群员
     */
    @JvmField
    val member: Member? = sender.user as Member?

    /**
     * 相关的群
     */
    val group: Group = (sender as TempMessageSender).group!!.user
    override fun printMessageLog() {
        log.info(
            "RM/Bot.{}:[{}({})] {}(Temp {}) >> {}", bot.id, group.data.name, group.id, member!!.data.memberName,
            member.id, this.toPlainText()
        )
    }

}