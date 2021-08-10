package forpleuvoir.dhwu1a.core.event.message

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.common.data.Subject
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent

/**
 * 戳一戳事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event
 *
 * #class_name NudgeEvent
 *
 * #create_time 2021/7/4 2:29
 */
class NudgeEvent(`object`: JsonObject) : Dhwu1aEvent() {
    /**
     * 戳一戳发起人 QQ 号
     */
    @SerializedName(FROM_ID)
    val fromId: Long

    /**
     * 被戳人的 QQ 号
     */
    @SerializedName(TARGET)
    val target: Long

    /**
     * 戳一戳事件发生的主体 (上下文)
     */
    @SerializedName(SUBJECT)
    val subject: Subject

    /**
     * 动作
     */
    @SerializedName(ACTION)
    val action: String

    /**
     * 后缀
     */
    @SerializedName(SUFFIX)
    val suffix: String
    override fun toPlainText(): String {
        return String.format(
            "%s[fromId:%d,target:%d,action:%s,suffix:%s,subject:%s]", type, fromId, target, action, suffix,
            subject.toPlainText()
        )
    }

    companion object {
        /**
         * 事件类型
         */
        @SerializedName(TYPE)
        val type = "NudgeEvent"
    }

    init {
        fromId = `object`[FROM_ID].asLong
        target = `object`[TARGET].asLong
        subject = Subject(`object`[SUBJECT].asJsonObject)
        action = `object`[ACTION].asString
        suffix = `object`[SUFFIX].asString
    }
}