package forpleuvoir.dhwu1a.core.message

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.message.base.Message
import forpleuvoir.dhwu1a.core.message.base.MessageType
import forpleuvoir.dhwu1a.core.message.messagesender.OtherClientMessageSender

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message
 *
 * #class_name OtherClientMessage
 *
 * #create_time 2021/7/1 22:46
 */
class OtherClientMessage(`object`: JsonObject) : Message(MessageType.OtherClientMessage, `object`) {
    override fun printMessageLog() {
        log.info(
            "RM/Bot.{} OtherClient[{}] >> {}", bot.id, (sender as OtherClientMessageSender).platform,
            toPlainText()
        )
    }
}