package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Quote
 *
 * #create_time 2021/6/30 00:10
 */
class Quote(
    /**
     * 被引用回复的原消息的messageId
     */
    @field:SerializedName(ID) val id: Long,
    /**
     * 被引用回复的原消息所接收的群号，当为好友消息时为0
     */
    @field:SerializedName(GROUP_ID) val groupId: Long,
    /**
     * 被引用回复的原消息的发送者的QQ号
     */
    @field:SerializedName(SENDER_ID) val senderId: Long,
    /**
     * 被引用回复的原消息的接收者者的QQ号（或群号）
     */
    @field:SerializedName(TARGET_ID) val targetId: Long,
    /**
     * 被引用回复的原消息的消息链对象
     */
    @field:SerializedName(ORIGIN) val origin: List<Plain>
) : MessageItem(MessageItemType.Quote) {
    override fun toPlainText(): String {
        return String.format("回复[%s]:", originPlainText)
    }

    private val originPlainText: String
        get() {
            val stringBuilder = StringBuilder()
            for (plain in origin) {
                stringBuilder.append(plain.toPlainText())
            }
            return stringBuilder.toString()
        }
}