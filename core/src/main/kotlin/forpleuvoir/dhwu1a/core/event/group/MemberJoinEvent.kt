package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * 新人入群的事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberJoinEvent
 *
 * #create_time 2021/7/3 23:48
 */
class MemberJoinEvent private constructor(member: MemberData) : GroupEvent(GroupEventType.MemberJoinEvent) {
    /**
     * 新人信息
     *
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