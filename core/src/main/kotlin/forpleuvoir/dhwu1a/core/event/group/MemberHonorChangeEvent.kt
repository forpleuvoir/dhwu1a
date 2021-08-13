package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.ACTION
import forpleuvoir.dhwu1a.core.common.HONOR
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 群员称号改变
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberHonorChangeEvent
 *
 * #create_time 2021/7/4 1:24
 */
class MemberHonorChangeEvent private constructor(
    member: MemberData,
    action: Action,
    honor: String
) : GroupEvent(GroupEventType.MemberHonorChangeEvent) {
    /**
     * 群员信息
     */
    @SerializedName(MEMBER)
    val member: MemberData

    /**
     * 称号变化行为：achieve获得成好，lose失去称号
     */
    @SerializedName(ACTION)
    val action: Action

    /**
     * 称号名称
     */
    @SerializedName(HONOR)
    val honor: String
    override fun getGroup(): Group {
        return member.getGroup()
    }

    override fun getMember(): Member {
        return getGroup().getMember(member.id)!!
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[action:%s,honor:%s,member:%d,group:%d]",
            type,
            action,
            honor,
            member.id,
            member.group.id
        )
    }

    enum class Action {
        achieve, lose
    }

    init {
        this.member = member
        this.action = action
        this.honor = honor
    }
}