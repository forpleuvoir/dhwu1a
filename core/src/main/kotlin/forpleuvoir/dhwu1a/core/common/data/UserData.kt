package forpleuvoir.dhwu1a.core.common.data

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.Dhwu1a.Companion.instance
import forpleuvoir.dhwu1a.core.common.ID
import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.user.base.User

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.common.data
 *
 * #class_name UserData
 *
 * #create_time 2021/7/3 21:53
 */
abstract class UserData<U : User?>(@field:SerializedName(ID) val id: Long) : IJsonData {
    abstract val user: U?

    companion object {
        @JvmField
        @Transient
        val bot = instance!!.bot
    }
}