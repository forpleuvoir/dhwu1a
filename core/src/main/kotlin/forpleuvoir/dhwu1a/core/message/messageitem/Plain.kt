package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.TEXT
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Plain
 *
 * #create_time 2021/6/30 0:26
 */
class Plain(
    /**
     * 文字消息
     */
    @field:SerializedName(TEXT) val text: String
) : MessageItem(MessageItemType.Plain) {
    override fun toPlainText(): String {
        return text
    }
}