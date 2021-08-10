package forpleuvoir.dhwu1a.core.common.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.ID
import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.common.KIND
import forpleuvoir.dhwu1a.core.message.base.IPlainText

/**
 * 戳一戳事件发生的主体 (上下文)
 *
 * @author forpleuvoir
 *
 * #project_name dhwuia
 *
 * #package forpleuvoir.dhwuia.core.event.data
 *
 * #class_name Subject
 *
 * #create_time 2021/4/18 2:42
 */
class Subject(`object`: JsonObject) : IJsonData, IPlainText {
    /**
     * 事件发生主体的 ID (群号 / 好友 QQ 号)
     */
    @SerializedName(ID)
    val id: Long

    /**
     * 戳一戳事件发生的主体的类型, 可以为 Group 和 Friend
     */
    @SerializedName(KIND)
    val kind: SubjectType
    override fun toPlainText(): String {
        return String.format("subject(id:%d,kind:%s)", id, kind)
    }

    /**
     * 戳一戳事件发生的主体的类型
     */
    enum class SubjectType {
        Group, Friend, Stranger
    }

    init {
        id = `object`[ID].asLong
        kind = SubjectType.valueOf(`object`[KIND].asString)
    }
}