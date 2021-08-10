package forpleuvoir.dhwu1a.core.message.base

import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem

/**
 * 消息发送器对象接口
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.core.message.base

 * #class_name IMessageSenderObject

 * #create_time 2021/8/11 0:25
 */
interface IMessageSenderObject {
    fun sendMessage(messageChain: List<MessageItem>, messageIdConsumer: ((Int) -> Unit)?)

    fun isQuoted(id: Int): IMessageSenderObject
}