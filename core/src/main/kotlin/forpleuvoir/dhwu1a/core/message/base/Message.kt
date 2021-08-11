package forpleuvoir.dhwu1a.core.message.base

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.Dhwu1a
import forpleuvoir.dhwu1a.core.common.MESSAGE_CHAIN
import forpleuvoir.dhwu1a.core.common.SENDER
import forpleuvoir.dhwu1a.core.common.TYPE
import forpleuvoir.dhwu1a.core.message.*
import forpleuvoir.dhwu1a.core.message.messageitem.Source
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog

/**
 * 消息
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.core.message.base

 * #class_name Message

 * #create_time 2021-08-10 20:04
 */
abstract class Message(
    /**
     * 消息类型
     */
    val type: MessageType, `object`: JsonObject
) : IMessage {
    companion object {
        private val LOG = Dhwu1aLog(this::class.java)

        @JvmStatic
        fun parse(obj: JsonObject): Message? {
            return try {
                when (MessageType.valueOf(obj[TYPE].asString)) {
                    MessageType.FriendMessage -> FriendMessage(obj)
                    MessageType.GroupMessage -> GroupMessage(obj)
                    MessageType.TempMessage -> TempMessage(obj)
                    MessageType.StrangerMessage -> StrangerMessage(obj)
                    MessageType.OtherClientMessage -> OtherClientMessage(obj)
                }
            } catch (e: Exception) {
                LOG.error(e.message, e)
                null
            }
        }

        @JvmStatic
        fun toPlainText(messageChan: List<MessageItem>): String {
            val stringBuilder = StringBuilder()
            messageChan.stream()
                .filter { it?.type != MessageItemType.Source }
                .forEach {
                    stringBuilder.append(it?.toPlainText())
                }
            return stringBuilder.toString()
        }
    }

    /**
     * 日志对象
     */
    val log: Dhwu1aLog = Dhwu1aLog(this::class.java)

    /**
     * 接收消息的Bot
     */
    @Transient
    val bot: Bot = Dhwu1a.instance.bot

    /**
     * 消息发送者
     */
    val sender: MessageSender

    /**
     * 消息链
     */
    val messageChain: List<MessageItem?>

    /**
     * 消息源
     */
    val source: Source

    /**
     * 消息id
     */
    val messageId: Int

    init {
        this.sender = MessageSender.parse(type, `object`[SENDER].asJsonObject)
        this.messageChain = MessageItem.parse(`object`.getAsJsonArray(MESSAGE_CHAIN))
        this.source = messageChain.stream().filter {
            it?.type == MessageItemType.Source
        }.findFirst().orElse(null) as Source
        this.messageId = source.id
    }


    fun quote(messageChain: List<MessageItem>, messageIdConsumer: ((Int?) -> Unit)?) {
        sender.user?.quote(messageId, messageChain, messageIdConsumer)
    }

    fun quote(vararg messageChain: MessageItem, messageIdConsumer: ((Int?) -> Unit)?) {
        quote(listOf(*messageChain), messageIdConsumer)
    }

    override fun toPlainText(): String {
        val stringBuilder = StringBuilder()
        messageChain.stream()
            .filter { it?.type != MessageItemType.Source }
            .forEach {
                stringBuilder.append(it?.toPlainText())
            }
        return stringBuilder.toString()
    }
}