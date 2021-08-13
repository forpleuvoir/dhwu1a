package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 群消息撤回
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name GroupRecallEvent
 *
 * #create_time 2021/7/3 23:11
 */
class GroupRecallEvent private constructor(
    /**
     * 原消息发送者的QQ号
     */
    @field:SerializedName(AUTHOR_ID) val authorId: Long,
    /**
     * 原消息messageId
     */
    @field:SerializedName(MESSAGE_ID) val messageId: Int,
    /**
     * 原消息发送时间
     */
    @field:SerializedName(TIME) val time: Int,
    group: GroupData,
    operator: OperatorData?
) : GroupEvent(GroupEventType.GroupRecallEvent) {
    /**
     * 消息撤回所在的群
     */
    @SerializedName(GROUP)
    val group: GroupData

    /**
     * 撤回消息的操作人，当null时为bot操作
     */
    @SerializedName(OPERATOR)
    val operator: OperatorData?

    override fun getGroup(): Group {
        return group.user
    }

    override fun getMember(): Member? {
        operator?.let { return getGroup().getMember(operator.id) }
        return null
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[messageId:%d,group:%d,operator:%d]", type, messageId, group.id,
            operator?.id ?: bot.id
        )
    }

    init {
        this.group = group
        this.operator = operator
    }
}