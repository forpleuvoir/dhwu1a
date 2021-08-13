package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.CURRENT
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.ORIGIN
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 群名片改动
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberCardChangeEvent
 *
 * #create_time 2021/7/4 1:06
 */
class MemberCardChangeEvent private constructor(
    /**
     * 原本名片
     */
    @field:SerializedName(ORIGIN) val origin: String,
    /**
     * 现在名片
     */
    @field:SerializedName(CURRENT) val current: String,
    member: MemberData
) : GroupEvent(GroupEventType.MemberCardChangeEvent) {

    override fun callback() {
        getMember().data.memberName = current
    }

    override fun getMember(): Member {
        return getGroup().getMember(member.id)!!
    }

    /**
     * 名片改动的群员的信息
     */
    @SerializedName(MEMBER)
    val member: MemberData
    override fun getGroup(): Group {
        return member.getGroup()
    }

    override fun toPlainText(): String {
        return String.format(
            "%s[origin:%s,current:%s,member:%d,group:%d]", type, origin, current, member.id,
            member.group.id
        )
    }

    init {
        this.member = member
    }
}