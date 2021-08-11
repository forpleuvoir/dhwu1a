package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * Bot被取消禁言
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name BotUnmuteEvent
 *
 * #create_time 2021/7/3 22:59
 */
class BotUnmuteEvent private constructor(operator: OperatorData) : GroupEvent(GroupEventType.BotUnmuteEvent) {
    /**
     * 操作的管理员或群主信息
     */
    @SerializedName(OPERATOR)
    val operator: OperatorData
    override fun getGroup(): Group {
        return operator.getGroup()
    }

    override fun toPlainText(): String {
        return String.format("%s[operator:%d,group:%d]", type, operator.id, operator.group.id)
    }

    init {
        this.operator = operator
    }
}