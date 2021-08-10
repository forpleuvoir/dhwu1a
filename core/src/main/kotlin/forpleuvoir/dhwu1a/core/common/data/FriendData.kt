package forpleuvoir.dhwu1a.core.common.data

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.NICKNAME
import forpleuvoir.dhwu1a.core.common.REMARK
import forpleuvoir.dhwu1a.core.user.Friend

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.common.data
 *
 * #class_name FriendData
 *
 * #create_time 2021/7/3 21:53
 */
class FriendData(
    id: Long?,
    /**
     * 好友的昵称
     */
    @field:SerializedName(NICKNAME) val nickname: String,
    /**
     * 好友的备注
     */
    @field:SerializedName(REMARK) val remark: String
) : UserData<Friend?>(id!!) {
    override val user: Friend?
        get() {
            assert(bot != null)
            return bot!!.getFriend(id)
        }
}