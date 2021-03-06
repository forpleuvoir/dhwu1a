package forpleuvoir.dhwu1a.core.websocket

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent
import forpleuvoir.dhwu1a.core.event.bot.BotEvent
import forpleuvoir.dhwu1a.core.event.bot.BotEventType
import forpleuvoir.dhwu1a.core.event.friend.FriendEvent
import forpleuvoir.dhwu1a.core.event.friend.FriendEventType
import forpleuvoir.dhwu1a.core.event.group.GroupEvent
import forpleuvoir.dhwu1a.core.event.group.GroupEventType
import forpleuvoir.dhwu1a.core.event.message.NudgeEvent
import forpleuvoir.dhwu1a.core.event.request.RequestEvent
import forpleuvoir.dhwu1a.core.event.request.RequestEventType
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.util.URLUtils
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient

/**
 * 时间websocket客户端
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.websocket
 *
 * #class_name EventWSC
 *
 * #create_time 2021/6/28 22:09
 */
class EventWSC(bot: Bot, ip: String, port: Int, verifyKey: String) : Dhwu1aWebSocketClient(
    String.format(
        "%s/%s?%s=%s&%s=%d",
        URLUtils.getWSUrl(ip, port),
        EVENT,
        VERIFY_KEY, verifyKey,
        QQ, bot.id
    ), bot, EVENT
) {
    companion object {
        @Transient
        private val log: Dhwu1aLog = Dhwu1aLog(Dhwu1aWebSocketClient::class.java)
        fun getInstance(bot: Bot, ip: String, port: Int, verifyKey: String): EventWSC {
            return EventWSC(bot, ip, port, verifyKey)
        }
    }

    override fun onMessageAsync(jsonObject: JsonObject) {
        if (jsonObject[SYNC_ID].asString == "") return
        val getData = JsonUtil.gson.fromJson(jsonObject, GetData::class.java)
        if (getData.isCallback) return
        this.addTask {
            val type = getData.data[TYPE].asString
            var event: Dhwu1aEvent? = null
            if (BotEventType.hasKey(type)) {
                event = BotEvent.parse(getData.data)
            } else if (FriendEventType.hasKey(type)) {
                event = FriendEvent.parse(getData.data)
            } else if (GroupEventType.hasKey(type)) {
                event = GroupEvent.parse(getData.data)
            } else if (RequestEventType.hasKey(type)) {
                event = RequestEvent.parse(getData.data)
            } else if (NudgeEvent.type == type) {
                event = NudgeEvent(getData.data)
            }
            event?.let { eventBus.broadcast(it) }
            true
        }
        executeTask()
    }

}