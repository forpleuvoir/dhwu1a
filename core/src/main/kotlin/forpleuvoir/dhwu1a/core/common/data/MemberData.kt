package forpleuvoir.dhwu1a.core.common.data

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member
import forpleuvoir.dhwu1a.core.user.base.Permission

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.common.data
 *
 * #class_name MemberData
 *
 * #create_time 2021/7/3 22:07
 */
class MemberData(
    id: Long?,
    /**
     * 操作者的群名片
     */
    @field:SerializedName(MEMBER_NAME) val memberName: String,
    /**
     * 群头衔
     */
    @field:SerializedName(SPECIAL_TITLE) val specialTitle: String,
    /**
     * 操作者在群中的权限，OWNER、ADMINISTRATOR或MEMBER
     */
    @field:SerializedName(PERMISSION) val permission: Permission,
    /**
     * 入群时间
     */
    @field:SerializedName(JOIN_TIMESTAMP) val joinTimestamp: Int,
    /**
     * 最后发言事件
     */
    @field:SerializedName(LAST_SPEAK_TIMESTAMP) val lastSpeakTimestamp: Int,
    /**
     * 禁言剩余时间
     */
    @field:SerializedName(MUTE_TIME_REMAINING) val muteTimeRemaining: Int,
    /**
     * 所在群信息
     */
    @field:SerializedName(GROUP) val group: GroupData
) : UserData<Member?>(id!!) {
    fun getGroup(): Group {
        return group.user
    }

    override val user: Member?
        get() = try {
            group.user.getMember(id)
        } catch (ignored: Exception) {
            null
        }
}