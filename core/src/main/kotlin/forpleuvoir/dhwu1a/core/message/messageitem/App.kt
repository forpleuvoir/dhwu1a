package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name App
 *
 * #create_time 2021/6/30 0:28
 */
class App(
    /**
     * App内容
     */
    @field:SerializedName(CONTENT) val content: String
) : MessageItem(MessageItemType.App) {
    override fun toPlainText(): String {
        return "[APP]"
    }
}