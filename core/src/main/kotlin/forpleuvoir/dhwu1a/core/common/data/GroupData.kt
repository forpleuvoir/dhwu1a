package forpleuvoir.dhwu1a.core.common.data

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.NAME
import forpleuvoir.dhwu1a.core.common.PERMISSION
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.base.Permission

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.common.data
 *
 * #class_name GroupData
 *
 * #create_time 2021/7/3 21:58
 */
class GroupData(
    id: Long?,
    /**
     * 群名称
     */
    @field:SerializedName(NAME) var name: String,
    /**
     * Bot在群里的权限
     */
    @field:SerializedName(PERMISSION) var permission: Permission
) : UserData<Group>(
    id!!
) {
    override val user: Group
        get() = bot.getGroup(id)!!
}