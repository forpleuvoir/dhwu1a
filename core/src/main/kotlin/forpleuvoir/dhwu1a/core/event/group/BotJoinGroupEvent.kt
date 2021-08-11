package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.GROUP
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * Bot加入了一个新群
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name BotJoinGroupEvent
 *
 * #create_time 2021/7/3 23:02
 */
class BotJoinGroupEvent private constructor(group: GroupData) : GroupEvent(GroupEventType.BotJoinGroupEvent) {
    /**
     * Bot新加入群的信息
     */
    @SerializedName(GROUP)
    val group: GroupData

    override fun getGroup(): Group {
        return group.user
    }

    override fun toPlainText(): String {
        return String.format("%s[group:%d]", type, group.id)
    }

    init {
        this.group = group
    }
}