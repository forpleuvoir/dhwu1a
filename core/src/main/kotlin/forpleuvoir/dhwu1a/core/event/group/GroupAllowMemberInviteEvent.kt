package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.CURRENT
import forpleuvoir.dhwu1a.core.common.GROUP
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.ORIGIN
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 允许群员邀请好友加群
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name GroupAllowMemberInviteEvent
 *
 * #create_time 2021/7/3 23:40
 */
class GroupAllowMemberInviteEvent private constructor(
    /**
     * 原本是否允许群员邀请好友加群
     */
    @field:SerializedName(ORIGIN) val origin: Boolean,
    /**
     * 现在是否允许群员邀请好友加群
     */
    @field:SerializedName(CURRENT) val current: Boolean,
    group: GroupData,
    operator: OperatorData?
) : GroupEvent(GroupEventType.GroupAllowMemberInviteEvent) {
    /**
     * 允许群员邀请好友加群状态改变的群信息
     */
    @SerializedName(GROUP)
    val group: GroupData

    override fun getMember(): Member? {
        operator?.let { return getGroup().getMember(operator.id) }
        return null
    }

    /**
     * 操作的管理员或群主信息，当null时为Bot操作
     */
    @SerializedName(OPERATOR)
    val operator: OperatorData?
    override fun getGroup(): Group {
        return group.user
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[origin:%b,current:%b,group:%d,operator:%d]", type, origin, current, group.id,
            operator?.id ?: bot.id
        )
    }

    init {
        this.group = group
        this.operator = operator
    }
}