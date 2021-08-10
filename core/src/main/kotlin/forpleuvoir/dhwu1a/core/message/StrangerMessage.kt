package forpleuvoir.dhwu1a.core.message

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.message.base.Message
import forpleuvoir.dhwu1a.core.message.base.MessageType
import forpleuvoir.dhwu1a.core.message.messagesender.StrangerMessageSender

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message
 *
 * #class_name StrangerMessage
 *
 * #create_time 2021/7/1 22:18
 */
class StrangerMessage(`object`: JsonObject) : Message(MessageType.StrangerMessage, `object`) {
    override fun printMessageLog() {
        log.info(
            "M/Bot.{} 陌生人[{}({})] >> {}", bot.id, (sender as StrangerMessageSender).nickname, sender.id,
            toPlainText()
        )
    }
}