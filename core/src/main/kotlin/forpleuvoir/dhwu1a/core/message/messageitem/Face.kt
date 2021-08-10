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
 * #class_name Face
 *
 * #create_time 2021/6/30 0:25
 */
class Face(
    /**
     * QQ表情编号，可选，优先高于name
     */
    @field:SerializedName(FACE_ID) val faceId: Int,
    /**
     * QQ表情拼音，可选
     */
    @field:SerializedName(NAME) val name: String?
) : MessageItem(MessageItemType.Face) {
    override fun toPlainText(): String {
        return String.format("/%s", name ?: faceId)
    }
}