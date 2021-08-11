package forpleuvoir.dhwu1a.core.event.group

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.CURRENT
import forpleuvoir.dhwu1a.core.common.MEMBER
import forpleuvoir.dhwu1a.core.common.ORIGIN
import forpleuvoir.dhwu1a.core.common.data.MemberData
import forpleuvoir.dhwu1a.core.user.Group

/**
 * 群头衔改动（只有群主有操作限权）
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name MemberSpecialTitleChangeEvent
 *
 * #create_time 2021/7/4 1:11
 */
class MemberSpecialTitleChangeEvent private constructor(
    /**
     * 原头衔
     */
    @field:SerializedName(ORIGIN) val origin: String,
    /**
     * 现头衔
     */
    @field:SerializedName(CURRENT) val current: String,
    member: MemberData
) : GroupEvent(GroupEventType.MemberSpecialTitleChangeEvent) {
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