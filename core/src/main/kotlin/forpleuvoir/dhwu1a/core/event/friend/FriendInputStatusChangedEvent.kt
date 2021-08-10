package forpleuvoir.dhwu1a.core.event.friend

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.INPUTTING
import forpleuvoir.dhwu1a.core.common.data.FriendData

/**
 * 好友输入状态改变
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.friend
 *
 * #class_name FriendInputStatusChangedEvent
 *
 * #create_time 2021/7/3 20:59
 */
class FriendInputStatusChangedEvent(
    friend: FriendData?,
    /**
     * 输入状态
     */
    @field:SerializedName(INPUTTING) val inputting: Boolean
) : FriendEvent(FriendEventType.FriendInputStatusChangedEvent, friend) {
    override fun toPlainText(): String {
        return String.format("%s[%b]", type, inputting)
    }
}