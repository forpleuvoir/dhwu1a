package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.JSON
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType
import forpleuvoir.dhwu1a.core.util.toJsonStr

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Json
 *
 * #create_time 2021/6/30 0:27
 */
class Json : MessageItem {
    /**
     * Json文本
     */
    @SerializedName(JSON)
    val json: String

    constructor(json: String) : super(MessageItemType.Json) {
        this.json = json
    }

    constructor(json: JsonElement) : super(MessageItemType.Json) {
        this.json = json.toJsonStr()
    }

    override fun toPlainText(): String {
        return json
    }
}