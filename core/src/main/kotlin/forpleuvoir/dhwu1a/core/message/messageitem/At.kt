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
 * #class_name At
 *
 * #create_time 2021/6/30 0:11
 */
class At @JvmOverloads constructor(
    /**
     * 群员QQ号
     */
    @field:SerializedName(TARGET) val target: Long,
    /**
     * At时显示的文字，发送消息时无效，自动使用群名片
     */
    @field:SerializedName(DISPLAY) val display: String? = null
) : MessageItem(MessageItemType.At) {
    override fun toPlainText(): String {
        return String.format("@%s", if (display == null || display == "") target else display)
    }
}