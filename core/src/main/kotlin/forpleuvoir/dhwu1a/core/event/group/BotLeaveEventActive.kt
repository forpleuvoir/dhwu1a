package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.GROUP
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * Bot主动退出一个群
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name BotLeaveEventActive
 *
 * #create_time 2021/7/3 23:05
 */
class BotLeaveEventActive private constructor(group: GroupData) : GroupEvent(GroupEventType.BotLeaveEventActive) {
    /**
     * Bot退出的群的信息
     */
    @SerializedName(GROUP)
    val group: GroupData

    /**
     * 退出群之后则不再能获取到该群对象
     *
     * @return null
     */
    override fun getGroup(): Group? {
        return null
    }

    override fun toPlainText(): String {
        return String.format("%s[group:%d]", type, group.id)
    }

    init {
        this.group = group
    }
}