package forpleuvoir.dhwu1a.core.message.messagesender

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.NICKNAME
import forpleuvoir.dhwu1a.core.common.REMARK
import forpleuvoir.dhwu1a.core.message.base.MessageSender
import forpleuvoir.dhwu1a.core.user.Friend

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messagesender
 *
 * #class_name FriendMessageSender
 *
 * #create_time 2021/7/1 21:19
 */
open class FriendMessageSender protected constructor(id: Long) : MessageSender(id) {
    @SerializedName(NICKNAME)
    var nickname: String? = null

    @SerializedName(REMARK)
    var remark: String? = null
    override val user: Friend
        get() = bot.getFriend(this.id)!!
}