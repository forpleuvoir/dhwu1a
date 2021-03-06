package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.DURATION_SECONDS
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * Bot被禁言
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name BotMuteEvent
 *
 * #create_time 2021/7/3 22:44
 */
class BotMuteEvent private constructor(
    /**
     * 禁言时长，单位为秒
     */
    @field:SerializedName(DURATION_SECONDS) val durationSeconds: Int,
    operator: OperatorData
) : GroupEvent(GroupEventType.BotMuteEvent) {
    /**
     * 操作的管理员或群主信息
     */
    @SerializedName(OPERATOR)
    val operator: OperatorData

    override fun getMember(): Member {
        return getGroup().getMember(operator.id)!!
    }

    override fun getGroup(): Group {
        return operator.getGroup()
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[time:%d,operator:%d,group:%d]", type, durationSeconds, operator.id,
            operator.group.id
        )
    }

    init {
        this.operator = operator
    }
}