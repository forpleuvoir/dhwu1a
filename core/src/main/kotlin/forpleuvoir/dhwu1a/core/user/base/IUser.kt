package forpleuvoir.dhwu1a.core.user.base

import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import javax.annotation.Nonnull

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.user
 *
 * #class_name IUser
 *
 * #create_time 2021/6/29 22:31
 */
interface IUser : IJsonData {
    fun sendMessage(@Nonnull messageChain: List<MessageItem>, messageIdConsumer: ((Int?) -> Unit)?)

    fun sendMessage(@Nonnull messageChain: List<MessageItem>)

    fun sendMessage(@Nonnull vararg messageChain: MessageItem) {
        sendMessage(listOf(*messageChain))
    }

    fun sendMessage(@Nonnull vararg messageChain: MessageItem, messageIdConsumer: ((Int?) -> Unit)?) {
        sendMessage(listOf(*messageChain), messageIdConsumer)
    }

    fun quote(quoteId: Int, @Nonnull messageChain: List<MessageItem>, messageIdConsumer: ((Int?) -> Unit)?)

    fun quote(quoteId: Int, @Nonnull vararg messageChain: MessageItem, messageIdConsumer: ((Int?) -> Unit)?) {
        quote(quoteId, listOf(*messageChain), messageIdConsumer)
    }
}