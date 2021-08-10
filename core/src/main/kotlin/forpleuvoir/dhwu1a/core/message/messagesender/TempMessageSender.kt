package forpleuvoir.dhwu1a.core.message.messagesender

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.message.base.MessageSender
import forpleuvoir.dhwu1a.core.user.Member
import forpleuvoir.dhwu1a.core.user.base.Permission

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messagesender
 *
 * #class_name TempMessageSender
 *
 * #create_time 2021/7/1 21:59
 */
open class TempMessageSender protected constructor(id: Long) : MessageSender(id) {
    @SerializedName(MEMBER_NAME)
    var memberName: String? = null

    @SerializedName(SPECIAL_TITLE)
    var specialTitle: String? = null

    @SerializedName(PERMISSION)
    var permission: Permission? = null

    @SerializedName(JOIN_TIMESTAMP)
    var joinTimestamp: Int? = null

    @SerializedName(LAST_SPEAK_TIMESTAMP)
    var lastSpeakTimestamp: Int? = null

    @SerializedName(MUTE_TIME_REMAINING)
    var muteTimeRemaining: Int? = null

    @JvmField
    @SerializedName(GROUP)
    var group: GroupData? = null
    override val user: Member?
        get() = if (group?.user != null) group!!.user.getMember(this.id) else null
}