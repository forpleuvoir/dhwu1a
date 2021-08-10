package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * 成员主动离群（该成员不是Bot）
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberLeaveEventQuit
 *
 * #create_time 2021/7/4 0:05
 */
class MemberLeaveEventQuit private constructor(member: MemberData) : GroupEvent(GroupEventType.MemberLeaveEventQuit) {
    /**
     * 退群群员的信息
     */
    @SerializedName(MEMBER)
    val member: MemberData
    override fun getGroup(): Group? {
        return member.getGroup()
    }

    override fun toPlainText(): String {
        return String.format("%s[member:%d,group:%d]", type, member.id, member.group.id)
    }

    init {
        this.member = member
    }
}