package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * 成员被踢出群（该成员不是Bot）
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberLeaveEventKick
 *
 * #create_time 2021/7/3 23:58
 */
class MemberLeaveEventKick private constructor(
    member: MemberData,
    operator: OperatorData
) : GroupEvent(GroupEventType.MemberLeaveEventKick) {
    /**
     * 被踢者的信息
     *
     */
    @SerializedName(MEMBER)
    val member: MemberData

    /**
     * 操作的管理员或群主信息，当null时为Bot操作
     */
    @SerializedName(OPERATOR)
    val operator: OperatorData?
    override fun getGroup(): Group? {
        return operator!!.getGroup()
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[member:%d,operator:%d]",
            type,
            member.id,
            operator?.id ?: bot!!.id
        )
    }

    init {
        this.member = member
        this.operator = operator
    }
}