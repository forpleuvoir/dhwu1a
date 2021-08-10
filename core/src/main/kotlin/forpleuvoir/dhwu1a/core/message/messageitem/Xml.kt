package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.XML
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Xml
 *
 * #create_time 2021/6/30 0:27
 */
class Xml(
    /**
     * XML文本
     */
    @field:SerializedName(XML) val xml: String
) : MessageItem(MessageItemType.Xml) {
    override fun toPlainText(): String {
        return "[XML]"
    }
}