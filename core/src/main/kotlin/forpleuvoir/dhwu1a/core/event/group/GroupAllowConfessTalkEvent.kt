package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * 坦白说
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name GroupAllowConfessTalkEvent
 *
 * #create_time 2021/7/3 23:36
 */
class GroupAllowConfessTalkEvent private constructor(
    /**
     * 原本坦白说是否开启
     */
    @field:SerializedName(ORIGIN) val origin: Boolean,
    /**
     * 现在坦白说是否开启
     */
    @field:SerializedName(CURRENT) val current: Boolean,
    group: GroupData,
    isByBot: Boolean
) : GroupEvent(GroupEventType.GroupAllowConfessTalkEvent) {
    /**
     * 坦白说状态改变的群信息
     */
    @SerializedName(GROUP)
    val group: GroupData

    /**
     * 是否Bot进行该操作
     */
    @SerializedName(IS_BY_BOT)
    val isByBot: Boolean
    override fun getGroup(): Group? {
        return group.user
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[origin:%b,current:%b,group:%d,isByBot:%b]", type, origin, current, group.id, isByBot
        )
    }

    init {
        this.group = group
        this.isByBot = isByBot
    }
}