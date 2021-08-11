package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.CURRENT
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.ORIGIN
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.base.Permission

/**
 * 成员权限改变的事件（该成员不是Bot）
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberPermissionChangeEvent
 *
 * #create_time 2021/7/4 1:13
 */
class MemberPermissionChangeEvent private constructor(
    /**
     * 原权限
     */
    @field:SerializedName(ORIGIN) val origin: Permission,
    /**
     * 现权限
     */
    @field:SerializedName(CURRENT) val current: Permission,
    member: MemberData
) : GroupEvent(GroupEventType.MemberPermissionChangeEvent) {
    /**
     * 名片改动的群员的信息
     */
    @SerializedName(MEMBER)
    val member: MemberData
    override fun getGroup(): Group {
        return member.getGroup()
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[origin:%s,current:%s,member:%d,group:%d]", type, origin, current, member.id,
            member.group.id
        )
    }

    init {
        this.member = member
    }
}