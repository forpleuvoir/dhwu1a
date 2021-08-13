package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 群成员被取消禁言事件（该成员不是Bot）
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberUnmuteEvent
 *
 * #create_time 2021/7/4 1:16
 */
class MemberUnmuteEvent private constructor(
    member: MemberData,
    operator: OperatorData
) : GroupEvent(GroupEventType.MemberUnmuteEvent) {
    /**
     * 被取消禁言的群员的信息
     */
    @SerializedName(MEMBER)
    val member: MemberData

    /**
     * 操作者的信息，当null时为Bot操作
     */
    @SerializedName(OPERATOR)
    val operator: OperatorData
    override fun getGroup(): Group {
        return operator.getGroup()
    }

    override fun getMember(): Member? {
        return getGroup().getMember(member.id)!!
    }

    override fun toPlainText(): String {
        return String.format("%s[member:%d,operator:%d,group:%d]", type, member.id, operator.id, operator.group.id)
    }

    init {
        this.member = member
        this.operator = operator
    }
}