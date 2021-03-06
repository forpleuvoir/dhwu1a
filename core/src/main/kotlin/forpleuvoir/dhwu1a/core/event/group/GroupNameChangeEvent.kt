package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.CURRENT
import forpleuvoir.dhwu1a.core.common.GROUP
import forpleuvoir.dhwu1a.core.common.OPERATOR
import forpleuvoir.dhwu1a.core.common.ORIGIN
import forpleuvoir.dhwu1a.core.common.data.GroupData
import forpleuvoir.dhwu1a.core.common.data.OperatorData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 某个群名改变
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name GroupNameChangeEvent
 *
 * #create_time 2021/7/3 23:18
 */
class GroupNameChangeEvent private constructor(

    /**
     * 原群名
     */
    @field:SerializedName(ORIGIN) val origin: String,
    /**
     * 新群名
     */
    @field:SerializedName(CURRENT) val current: String,
    group: GroupData,
    operator: OperatorData?
) : GroupEvent(GroupEventType.GroupNameChangeEvent) {

    override fun callback() {
        getGroup().data.name = current
    }

    /**
     * 群名改名的群信息
     */
    @SerializedName(GROUP)
    val group: GroupData

    /**
     * 操作的管理员或群主信息，当null时为Bot操作
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
            "%s[origin:%s,current:%s,group:%d,operator:%d]", type, origin, current, group.id,
            operator?.id ?: bot.id
        )
    }

    init {
        this.group = group
        this.operator = operator
    }
}