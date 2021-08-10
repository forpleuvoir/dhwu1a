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
 * #class_name Image
 *
 * #create_time 2021/6/30 0:26
 */
class Image(
    /**
     * 图片的imageId，群图片与好友图片格式不同。不为空时将忽略 [.url] 属性
     */
    @field:SerializedName(IMAGE_ID) val imageId: String,
    /**
     * 图片的URL，发送时可作网络图片的链接；接收时为腾讯图片服务器的链接，可用于图片下载
     */
    @field:SerializedName(URL) val url: String,
    /**
     * 图片的路径，发送本地图片，相对路径于plugins/MiraiAPIHTTP/images
     */
    @field:SerializedName(PATH) val path: String,
    /**
     * 图片的 Base64 编码
     */
    @field:SerializedName(BASE64) val base64: String
) : MessageItem(MessageItemType.Image) {
    override fun toPlainText(): String {
        return "[图片]"
    }
}