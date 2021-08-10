package forpleuvoir.dhwu1a.core.message.base

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.Dhwu1a
import forpleuvoir.dhwu1a.core.common.ID
import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.user.base.User
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.JsonUtil

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.base
 *
 * #class_name MessageSender
 *
 * #create_time 2021/7/1 21:15
 */
abstract class MessageSender(
    /**
     * 发送者QQ号
     */
    @field:SerializedName(ID) val id: Long
) : IJsonData {
    abstract val user: User?

    companion object {
        /**
         * 接收消息的Bot
         */
        @Transient
        val bot: Bot = Dhwu1a.instance!!.bot!!

        @JvmStatic
        fun parse(type: MessageType, `object`: JsonObject?): MessageSender {
            return JsonUtil.gson.fromJson(`object`, type.clazz)!!
        }
    }
}