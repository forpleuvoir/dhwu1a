package forpleuvoir.dhwu1a.core.event.friend

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.FROM
import forpleuvoir.dhwu1a.core.common.TO
import forpleuvoir.dhwu1a.core.common.data.FriendData

/**
 * 好友昵称改变
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.friend
 *
 * #class_name FriendNickChangedEvent
 *
 * #create_time 2021/7/3 21:06
 */
class FriendNickChangedEvent(
    friend: FriendData?,
    /**
     * 原昵称
     */
    @field:SerializedName(FROM) val from: String,
    /**
     * 修改后的昵称
     */
    @field:SerializedName(TO) val to: String
) : FriendEvent(FriendEventType.FriendNickChangedEvent, friend) {

    override fun callback() {
        friend?.let { bot.getFriend(friend.id)?.data?.nickname = to }
    }

    override fun toPlainText(): String {
        return String.format("%s[from:%s,to:%s]", type, from, to)
    }
}