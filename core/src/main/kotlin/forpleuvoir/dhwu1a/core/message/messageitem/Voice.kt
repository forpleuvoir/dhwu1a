package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.common.PATH
import forpleuvoir.dhwu1a.core.common.VOICE_ID
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Voice
 *
 * #create_time 2021/6/30 0:27
 */
class Voice(
    /**
     * 语音的voiceId，不为空时将忽略url属性
     */
    @field:SerializedName(VOICE_ID) val voiceId: String,
    /**
     * 语音的URL，发送时可作网络语音的链接；接收时为腾讯语音服务器的链接，可用于语音下载
     */
    @field:SerializedName(URL) val url: String,
    /**
     * 语音的路径，发送本地语音，相对路径于plugins/MiraiAPIHTTP/voices
     */
    @field:SerializedName(PATH) val path: String,
    /**
     * 语音的 Base64 编码
     */
    @field:SerializedName(BASE64) val base64: String
) : MessageItem(MessageItemType.Voice) {
    override fun toPlainText(): String {
        return "[语音消息]"
    }
}