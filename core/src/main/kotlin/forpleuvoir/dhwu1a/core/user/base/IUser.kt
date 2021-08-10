package forpleuvoir.dhwu1a.core.user.base

import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import java.util.function.Consumer
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
    fun sendMessage(messageId: Consumer<Int?>?, @Nonnull messageChain: List<MessageItem>)
    fun sendMessage(@Nonnull messageChain: List<MessageItem>)
    fun sendMessage(@Nonnull vararg messageChain: MessageItem) {
        sendMessage(listOf(*messageChain))
    }

    fun sendMessage(messageId: Consumer<Int?>?, @Nonnull vararg messageChain: MessageItem) {
        sendMessage(messageId, listOf(*messageChain))
    }

    fun quote(quoteId: Int?, messageId: Consumer<Int?>?, @Nonnull messageChain: List<MessageItem>)
    fun quote(quoteId: Int?, messageId: Consumer<Int?>?, @Nonnull vararg messageChain: MessageItem) {
        quote(quoteId, messageId, listOf(*messageChain))
    }
}