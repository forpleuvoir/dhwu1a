package forpleuvoir.dhwu1a.core.event.friend

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.AUTHOR_ID
import forpleuvoir.dhwu1a.core.common.MESSAGE_ID
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.TIME
import forpleuvoir.dhwu1a.core.user.Friend
import java.util.*

/**
 * 好友消息撤回
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.friend
 *
 * #class_name FriendRecallEvent
 *
 * #create_time 2021/7/3 22:12
 */
class FriendRecallEvent(
    /**
     * 原消息发送者的QQ号
     */
    @field:SerializedName(AUTHOR_ID) val authorId: Long,
    /**
     * 原消息messageId
     */
    @field:SerializedName(MESSAGE_ID) val messageId: Int,
    /**
     * 原消息发送时间
     */
    @field:SerializedName(TIME) val time: Int,
    /**
     * 好友QQ号或BotQQ号
     */
    @field:SerializedName(OPERATOR) val operator: Long
) : FriendEvent(FriendEventType.FriendRecallEvent, null) {
    override fun getFriend(): Friend? {
        return Optional.ofNullable(bot.getFriend(authorId)).orElse(null)
    }

    override fun toPlainText(): String {
        return String.format("%s[messageId:%d]", type, messageId)
    }
}