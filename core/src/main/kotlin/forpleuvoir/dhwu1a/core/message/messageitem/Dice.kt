package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * 骰子消息
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Dice
 *
 * #create_time 2021/7/1 1:08
 */
class Dice(
    /**
     * 点数
     */
    @field:SerializedName(VALUE) val value: Int
) : MessageItem(MessageItemType.Dice) {
    override fun toPlainText(): String {
        return String.format("骰子:%d点", value)
    }
}