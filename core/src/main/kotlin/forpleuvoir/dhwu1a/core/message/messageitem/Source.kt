package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.ID
import forpleuvoir.dhwu1a.core.common.TIME
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Source
 *
 * #create_time 2021/6/30 00:11
 */
class Source(
    /**
     * 消息的识别号，用于引用回复（Source类型永远为chain的第一个元素）
     */
    @field:SerializedName(ID) val id: Int, @field:SerializedName(TIME) val time: Int
) : MessageItem(MessageItemType.Source) {
    override fun toPlainText(): String {
        return String.format("[消息源(id:%d,time:%d)]", id, time)
    }
}