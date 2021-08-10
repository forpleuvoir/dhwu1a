package forpleuvoir.dhwu1a.core.message.base

import forpleuvoir.dhwu1a.core.Dhwu1a
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.message.base.Message.Companion.toPlainText
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member
import forpleuvoir.dhwu1a.core.user.base.User
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.ifHasKey
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.command.Command

/**
 * 消息发送器
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.core.message.base

 * #class_name MessageSenderObject

 * #create_time 2021/8/11 0:19
 */
class MessageSenderObject(
    private val command: Command,
    private val log: Dhwu1aLog,
    private val logFormat: String,
    user: User
) :
    IMessageSenderObject {
    private val bot: Bot = Dhwu1a.instance!!.bot!!
    private val params = HashMap<String, Any>()

    init {
        params[TARGET] = user.id
        params[QQ] = user.id
        if (user is Group)
            params[GROUP] = user.id
        else if (user is Member)
            params[GROUP] = user.group.id
    }

    override fun sendMessage(messageChain: List<MessageItem>, messageIdConsumer: ((Int) -> Unit)?) {
        params[MESSAGE_CHAIN] = messageChain
        bot.sendCommand(CommandSender(command, params)) { data ->
            if (data.ifHasKey(MESSAGE_ID)?.asInt == null) {
                log.info("SM/Bot.{}: {} << {}", bot.id, logFormat, toPlainText(messageChain))
                messageIdConsumer?.invoke(data[MESSAGE_CHAIN].asInt)
            }
        }
    }

    override fun isQuoted(id: Int): IMessageSenderObject {
        this.params[QUOTE] = id
        return this
    }


}