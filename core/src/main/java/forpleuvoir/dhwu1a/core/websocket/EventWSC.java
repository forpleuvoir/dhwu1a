package forpleuvoir.dhwu1a.core.websocket;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.event.NudgeEvent;
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent;
import forpleuvoir.dhwu1a.core.event.bot.BotEvent;
import forpleuvoir.dhwu1a.core.event.bot.BotEventType;
import forpleuvoir.dhwu1a.core.event.friend.FriendEvent;
import forpleuvoir.dhwu1a.core.event.friend.FriendEventType;
import forpleuvoir.dhwu1a.core.event.group.GroupEvent;
import forpleuvoir.dhwu1a.core.event.group.GroupEventType;
import forpleuvoir.dhwu1a.core.event.request.RequestEvent;
import forpleuvoir.dhwu1a.core.event.request.RequestEventType;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.URLUtils;
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient;

import java.net.URISyntaxException;
import java.util.Optional;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 时间websocket客户端
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket
 * <p>#class_name EventWSC
 * <p>#create_time 2021/6/28 22:09
 */
public class EventWSC extends Dhwu1aWebSocketClient {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(Dhwu1aWebSocketClient.class);

    public static EventWSC getInstance(Bot bot, String ip, int port, String verifyKey) {
        try {
            return new EventWSC(bot, ip, port, verifyKey);
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public EventWSC(Bot bot, String ip, int port, String verifyKey) throws URISyntaxException {
        super(String.format("%s/%s?%s=%s&%s=%d",
                URLUtils.getWSURL(ip, port),
                EVENT,
                VERIFY_KEY, verifyKey,
                QQ, bot.id
                ), bot, EVENT
        );
    }

    @Override
    public void onMessage(String data) {
        JsonObject jsonObject = JsonUtil.ifHasKey(data, SYNC_ID);
        Optional.ofNullable(jsonObject).ifPresent(object -> {
            if (jsonObject.get(SYNC_ID).getAsString().equals("")) return;
            GetData getData = JsonUtil.gson.fromJson(jsonObject, GetData.class);
            if (getData.isCallback()) {
                return;
            }
            String type = getData.data.get(TYPE).getAsString();
            Dhwu1aEvent event = null;
            if (BotEventType.hasKey(type)) {
                event = BotEvent.parse(getData.data);
            } else if (FriendEventType.hasKey(type)) {
                event = FriendEvent.parse(getData.data);
            } else if (GroupEventType.hasKey(type)) {
                event = GroupEvent.parse(getData.data);
            } else if (RequestEventType.hasKey(type)) {
                event = RequestEvent.parse(getData.data);
            } else if (NudgeEvent.type.equals(type)) {
                event = new NudgeEvent(getData.data);
            }
            Optional.ofNullable(event).ifPresent(eventBus::broadcast);
        });
    }
}
