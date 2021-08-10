package forpleuvoir.dhwu1a.core.message.messageitem.base

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.common.TYPE
import forpleuvoir.dhwu1a.core.message.base.IPlainText
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import java.util.*

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem.base
 *
 * #class_name MessageItem
 *
 * #create_time 2021/6/29 23:02
 */
abstract class MessageItem : IPlainText, IJsonData {
    @Transient
    protected var log: Dhwu1aLog? = null

    @JvmField
    @SerializedName(TYPE)
    val type: MessageItemType

    constructor(jsonObject: JsonObject) {
        type = MessageItemType.valueOf(jsonObject[TYPE].asString)
    }

    constructor(type: MessageItemType) {
        this.type = type
    }

    fun setLog(log: Dhwu1aLog?): MessageItem {
        this.log = log
        return this
    }

    companion object {
        @Transient
        private val LOG = Dhwu1aLog(MessageItem::class.java)

        @JvmStatic
        fun parse(array: JsonArray): List<MessageItem?> {
            return try {
                val list = LinkedList<MessageItem?>()
                for (element in array) {
                    list.add(parse(element.asJsonObject))
                }
                list
            } catch (e: Exception) {
                LOG.error(e.message, e)
                listOf<MessageItem>()
            }
        }

        @JvmStatic
        fun parse(jsonObject: JsonObject): MessageItem? {
            try {
                val messageItemType = MessageItemType.valueOf(jsonObject[TYPE].asString)
                return JsonUtil.gson.fromJson(jsonObject, messageItemType.clazz)
                    ?.setLog(Dhwu1aLog(messageItemType.clazz))
            } catch (e: Exception) {
                LOG.error("消息解析失败")
                LOG.error(e.message, e)
            }
            return null
        }
    }
}