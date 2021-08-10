package forpleuvoir.dhwu1a.core.user.base

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.Dhwu1a
import forpleuvoir.dhwu1a.core.common.ID
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.user.bot.Bot
import java.util.function.Consumer
import javax.annotation.Nonnull

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.user
 *
 * #class_name User
 *
 * #create_time 2021/6/29 22:32
 */
abstract class User protected constructor(
    /**
     * 用户ID(QQ号)
     */
    @field:SerializedName(ID) val id: Long
) : IUser {
    val bot: Bot = Dhwu1a.instance!!.bot!!
    override fun sendMessage(@Nonnull messageChain: List<MessageItem>) {
        messageSenderObject().sendMessage(messageChain, null)
    }

    override fun sendMessage(messageIdConsumer: Consumer<Int?>?, @Nonnull messageChain: List<MessageItem>) {
        messageSenderObject().sendMessage(messageChain) {
            messageIdConsumer?.accept(it)
        }
    }

    override fun quote(quoteId: Int, messageIdConsumer: Consumer<Int?>?, @Nonnull messageChain: List<MessageItem>) {
        messageSenderObject().isQuoted(quoteId).sendMessage(messageChain) {
            messageIdConsumer?.accept(it)
        }
    }

    abstract fun messageSenderObject(): MessageSenderObject
}