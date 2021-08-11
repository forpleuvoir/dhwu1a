package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.CURRENT
import forpleuvoir.dhwu1a.core.common.GROUP
import forpleuvoir.dhwu1a.core.common.ORIGIN
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.base.Permission

/**
 * Bot在群里的权限被改变. 操作人一定是群主
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name BotGroupPermissionChangeEvent
 *
 * #create_time 2021/7/3 21:46
 */
class BotGroupPermissionChangeEvent private constructor(
    /**
     * Bot的原权限，OWNER、ADMINISTRATOR或MEMBER
     */
    @field:SerializedName(ORIGIN) val origin: Permission,
    /**
     * Bot的新权限，OWNER、ADMINISTRATOR或MEMBER
     */
    @field:SerializedName(CURRENT) val current: Permission,
    /**
     * 权限改变所在的群信息
     */
    @field:SerializedName(GROUP) val group: GroupData
) : GroupEvent(GroupEventType.BotGroupPermissionChangeEvent) {
    override fun getGroup(): Group {
        return group.user
    }

    override fun toPlainText(): String {
        return String.format("%s[origin:%s,current:%s,group:%d]", type, origin, current, group.id)
    }
}