package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.message.base.IPlainText
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType
import forpleuvoir.dhwu1a.core.util.toJsonStr
import java.util.*
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference

/**
 * 转发消息
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.core.message.messageitem

 * #class_name Forward

 * #create_time 2021/8/10 22:53
 */
class Forward(val nodeList: List<NodeItem>) : MessageItem(MessageItemType.Forward) {

    constructor(vararg nodeItems: NodeItem) : this(listOf(*nodeItems))

    override fun toPlainText(): String {
        return String.format("转发消息:%d条", nodeList.size)
    }
}

@JsonAdapter(NodeItemAdapter::class)
open class NodeItem : IPlainText, IJsonData {

    /**
     * 发送者QQ号
     */
    @SerializedName(SENDER_ID)
    val senderId: Long

    /**
     * 发送时间 时间戳, 单位 秒
     */
    @SerializedName(TIME)
    val time: Long

    /**
     * 发送者名称
     */
    @SerializedName(SENDER_NAME)
    val senderName: String

    /**
     * 消息链
     */
    @SerializedName(MESSAGE_CHAIN)
    val messageChain: List<MessageItem>

    /**
     * 可以只使用消息messageId，从缓存中读取一条消息作为节点
     */
    @SerializedName(MESSAGE_ID)
    val messageId: Int?

    constructor(
        senderId: Long, time: Long, senderName: String,
        messageChain: List<MessageItem>, messageId: Int?
    ) {
        this.senderId = senderId
        this.time = time
        this.senderName = senderName
        this.messageChain = messageChain
        this.messageId = messageId
    }

    constructor(
        senderId: Long, time: Long, senderName: String,
        messageChain: List<MessageItem>
    ) {
        this.senderId = senderId
        this.time = time
        this.senderName = senderName
        this.messageChain = messageChain
        messageId = null
    }

    override fun toJsonString(): String {
        return this.toJsonStr()
    }

    override fun toPlainText(): String {
        return ""
    }

}

open class NodeItemAdapter : TypeAdapter<NodeItem>() {
    override fun write(out: JsonWriter?, value: NodeItem) {
        if (out != null) {
            out.beginObject()
            out.name(SENDER_ID).value(value.senderId)
            out.name(TIME).value(value.time)
            out.name(SENDER_NAME).value(value.senderName)
            out.name(MESSAGE_ID).value(value.messageId)
            out.name(MESSAGE_CHAIN).jsonValue(value.messageChain.toJsonStr())
            out.endObject()
        }
    }

    override fun read(`in`: JsonReader): NodeItem {
        val senderId = AtomicLong()
        val time = AtomicLong()
        val senderName = AtomicReference<String>()
        val messageChain = LinkedList<MessageItem>()
        var messageId: Int? = null
        `in`.beginObject()
        while (`in`.hasNext()) {
            when (`in`.nextName()) {
                SENDER_ID -> senderId.set(`in`.nextLong())
                TIME -> time.set(`in`.nextLong())
                SENDER_NAME -> senderName.set(`in`.nextString())
                MESSAGE_CHAIN -> {
                    `in`.beginArray()
                    while (`in`.hasNext()) {
                        `in`.beginObject()
                        val json = JsonObject()
                        while (`in`.hasNext()) {
                            val name = `in`.nextName()
                            val nextString: String? = try {
                                `in`.nextString()
                            } catch (e: IllegalStateException) {
                                `in`.nextNull()
                                null
                            }
                            json.addProperty(name, nextString)
                        }
                        `in`.endObject()
                        messageChain.add(MessageItem.parse(json)!!)
                    }
                    `in`.endArray()
                }
                MESSAGE_ID -> {
                    try {
                        messageId = `in`.nextInt()
                    } catch (ignored: IllegalStateException) {
                        `in`.nextNull()
                    }
                }

            }

        }
        `in`.endObject()
        return NodeItem(senderId.get(), time.get(), senderName.get(), messageChain, messageId)
    }
}