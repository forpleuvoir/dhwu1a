package forpleuvoir.dhwu1a.core.message.messagesender

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.PLATFORM
import forpleuvoir.dhwu1a.core.message.base.MessageSender
import forpleuvoir.dhwu1a.core.user.base.User

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messagesender
 *
 * #class_name OtherClientMessageSender
 *
 * #create_time 2021/7/1 22:43
 */
open class OtherClientMessageSender protected constructor(id: Long) : MessageSender(id) {
    /**
     * 平台
     */
    @JvmField
    @SerializedName(PLATFORM)
    var platform: String? = null
    override val user: User?
        get() = null
}