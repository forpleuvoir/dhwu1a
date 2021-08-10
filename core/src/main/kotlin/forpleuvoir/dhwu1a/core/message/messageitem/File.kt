package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.ID
import forpleuvoir.dhwu1a.core.common.NAME
import forpleuvoir.dhwu1a.core.common.SIZE
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name File
 *
 * #create_time 2021/6/30 0:29
 */
class File(
    /**
     * 文件识别id
     */
    @field:SerializedName(ID) val id: String,
    /**
     * 文件名
     */
    @field:SerializedName(NAME) val name: String,
    /**
     * 文件大小
     */
    @field:SerializedName(SIZE) val size: Long
) : MessageItem(MessageItemType.File) {
    override fun toPlainText(): String {
        return String.format("文件(%s)", name)
    }
}