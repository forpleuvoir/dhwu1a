package forpleuvoir.dhwu1a.core.websocket

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.MESSAGE
import forpleuvoir.dhwu1a.core.common.QQ
import forpleuvoir.dhwu1a.core.common.SYNC_ID
import forpleuvoir.dhwu1a.core.common.VERIFY_KEY
import forpleuvoir.dhwu1a.core.event.message.MessageEvent
import forpleuvoir.dhwu1a.core.message.base.Message
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.util.URLUtils
import forpleuvoir.dhwu1a.core.util.ifHasKey
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient
import java.net.URISyntaxException
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer

/**
 * 消息websocket客户端
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.websocket
 *
 * #class_name MessageWSC
 *
 * #create_time 2021/6/28 21:21
 */
class MessageWSC(bot: Bot, ip: String?, port: Int, verifyKey: String?) : Dhwu1aWebSocketClient(
    String.format(
        "%s/%s?%s=%s&%s=%d",
        URLUtils.getWSUrl(ip, port),
        MESSAGE,
        VERIFY_KEY, verifyKey,
        QQ, bot.id
    ), bot, MESSAGE
) {
    private val callbacks: ConcurrentHashMap<Int, Consumer<JsonObject>> = ConcurrentHashMap<Int, Consumer<JsonObject>>()
    fun sendMessage(sendObject: CommandSender, callback: Consumer<JsonObject>?) {
        this.send(sendObject.toMessageJsonString())
        if (callback != null) callbacks[sendObject.sendId] = callback
    }

    override fun onMessage(data: String) {
        val jsonObject: JsonObject? = data.ifHasKey(SYNC_ID)
        Optional.ofNullable<JsonObject>(jsonObject).ifPresent {
            if (jsonObject!![SYNC_ID].asString == "") return@ifPresent
            val getData = JsonUtil.gson.fromJson(jsonObject, GetData::class.java)
            if (!getData.isCallback) {
                val message = Message.parse(getData.data)
                Optional.ofNullable(message)
                    .ifPresent { message1: Message? ->
                        eventBus.broadcast(
                            MessageEvent.parse(message1!!)
                        )
                    }
            } else {
                if (callbacks.containsKey(getData.syncId)) {
                    try {
                        callbacks[getData.syncId]?.accept(getData.data)
                        callbacks.remove(getData.syncId)
                    } catch (e: Exception) {
                        log.error(e.message, e)
                    }
                }
            }
        }
    }

    companion object {
        @Transient
        private val log: Dhwu1aLog = Dhwu1aLog(MessageWSC::class.java)
        fun getInstance(bot: Bot, ip: String?, port: Int, verifyKey: String?): MessageWSC? {
            return try {
                MessageWSC(bot, ip, port, verifyKey)
            } catch (e: URISyntaxException) {
                log.error(e.message, e)
                null
            }
        }
    }
}