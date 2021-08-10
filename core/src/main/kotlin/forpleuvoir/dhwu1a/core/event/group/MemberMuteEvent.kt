package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.DURATION_SECONDS
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * 群成员被禁言事件（该成员不是Bot）
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberMuteEvent
 *
 * #create_time 2021/7/4 1:20
 */
class MemberMuteEvent private constructor(
    /**
     * 禁言时长，单位为秒
     */
    @field:SerializedName(DURATION_SECONDS) val durationSeconds: Long, member: MemberData,
    operator: OperatorData
) : GroupEvent(GroupEventType.MemberMuteEvent) {
    /**
     * 被消禁言的群员的信息
     */
    @SerializedName(MEMBER)
    val member: MemberData

    /**
     * 操作者的信息，当null时为Bot操作
     */
    @SerializedName(OPERATOR)
    val operator: OperatorData
    override fun getGroup(): Group? {
        return operator.getGroup()
    }

    override fun toPlainText(): String {
        return String.format("%s[member:%d,operator:%d,group:%d]", type, member.id, operator.id, operator.group.id)
    }

    init {
        this.member = member
        this.operator = operator
    }
}