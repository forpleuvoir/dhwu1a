package forpleuvoir.dhwu1a.core.message.messagesender

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.NICKNAME
import forpleuvoir.dhwu1a.core.common.REMARK
import forpleuvoir.dhwu1a.core.message.base.MessageSender
import forpleuvoir.dhwu1a.core.user.base.User

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messagesender
 *
 * #class_name StrangerMessageSender
 *
 * #create_time 2021/7/1 22:41
 */
open class StrangerMessageSender protected constructor(id: Long) : MessageSender(id) {
    @JvmField
    @SerializedName(NICKNAME)
    var nickname: String? = null

    @SerializedName(REMARK)
    var remark: String? = null
    override val user: User?
        get() = null
}