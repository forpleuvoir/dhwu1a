package forpleuvoir.dhwu1a.core.message.messagesender

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.message.base.MessageSender
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member
import forpleuvoir.dhwu1a.core.user.base.Permission

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messagesender
 *
 * #class_name GroupMessageSender
 *
 * #create_time 2021/7/1 21:56
 */
open class GroupMessageSender protected constructor(id: Long) : MessageSender(id) {
    @SerializedName(MEMBER_NAME)
    lateinit var memberName: String
        private set

    @SerializedName(SPECIAL_TITLE)
    lateinit var specialTitle: String
        private set

    @SerializedName(PERMISSION)
    lateinit var permission: Permission
        private set

    @SerializedName(JOIN_TIMESTAMP)
    var joinTimestamp: Int = 0
        private set

    @SerializedName(LAST_SPEAK_TIMESTAMP)
    var lastSpeakTimestamp: Int = 0
        private set

    @SerializedName(MUTE_TIME_REMAINING)
    var muteTimeRemaining: Int = 0
        private set

    @SerializedName(GROUP)
    private lateinit var group: GroupData
    override val user: Group
        get() = group.user
    val member: Member?
        get() = group.user.getMember(this.id)
}