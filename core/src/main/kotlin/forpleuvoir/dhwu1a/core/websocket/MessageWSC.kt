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
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient
import java.util.concurrent.ConcurrentHashMap

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
    companion object {
        @Transient
        private val log: Dhwu1aLog = Dhwu1aLog(MessageWSC::class.java)
        fun getInstance(bot: Bot, ip: String?, port: Int, verifyKey: String?): MessageWSC {
            return MessageWSC(bot, ip, port, verifyKey)
        }
    }

    private val callbacks: ConcurrentHashMap<Int, (JsonObject) -> Unit> = ConcurrentHashMap<Int, (JsonObject) -> Unit>()

    fun sendMessage(sendObject: CommandSender, callback: ((JsonObject) -> Unit)?) {
        this.send(sendObject.toMessageJsonString())
        if (callback != null) callbacks[sendObject.sendId] = callback
    }

    override fun onMessageAsync(jsonObject: JsonObject) {
        if (jsonObject[SYNC_ID].asString == "") return
        val getData = JsonUtil.gson.fromJson(jsonObject, GetData::class.java)
        if (!getData.isCallback) {
            this.addTask {
                Message.parse(getData.data)?.let {
                    eventBus.broadcast(
                        MessageEvent.parse(it)
                    )
                }
                true
            }
            executeTask()
        } else {
            if (callbacks.containsKey(getData.syncId)) {
                try {
                    callbacks[getData.syncId]?.invoke(getData.data)
                    callbacks.remove(getData.syncId)
                } catch (e: Exception) {
                    log.error(e.message, e)
                }
            }
        }
    }


}