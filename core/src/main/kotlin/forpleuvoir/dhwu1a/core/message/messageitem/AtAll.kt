package forpleuvoir.dhwu1a.core.message.messageitem

import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name AtAll
 *
 * #create_time 2021/6/30 0:25
 */
class AtAll : MessageItem(MessageItemType.AtAll) {
    override fun toPlainText(): String {
        return "@全体成员"
    }
}