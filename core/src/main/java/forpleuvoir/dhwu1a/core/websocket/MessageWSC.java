package forpleuvoir.dhwu1a.core.websocket;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.event.message.MessageEvent;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.URLUtils;
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender;
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient;

import javax.annotation.Nullable;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 消息websocket客户端
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket
 * <p>#class_name MessageWSC
 * <p>#create_time 2021/6/28 21:21
 */
public class MessageWSC extends Dhwu1aWebSocketClient {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(MessageWSC.class);
    private final ConcurrentHashMap<Integer, Consumer<JsonObject>> callbacks = new ConcurrentHashMap<>();

    public static MessageWSC getInstance(Bot bot, String ip, int port, String verifyKey) {
        try {
            return new MessageWSC(bot, ip, port, verifyKey);
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public MessageWSC(Bot bot, String ip, int port, String verifyKey) throws URISyntaxException {
        super(String.format("%s/%s?%s=%s&%s=%d",
                URLUtils.getWSURL(ip, port),
                MESSAGE,
                VERIFY_KEY, verifyKey,
                QQ, bot.id
                ), bot, MESSAGE
        );
    }

    public void sendMessage(CommandSender sendObject, @Nullable Consumer<JsonObject> callback) {
        this.send(sendObject.toMessageJsonString());
        if (callback != null)
            callbacks.put(sendObject.getSendId(), callback);
    }

    @Override
    public void onMessage(String data) {
        JsonObject jsonObject = JsonUtil.ifHasKey(data, SYNC_ID);
        Optional.ofNullable(jsonObject).ifPresent(object -> {
            if (jsonObject.get(SYNC_ID).getAsString().equals("")) return;
            GetData getData = JsonUtil.gson.fromJson(jsonObject, GetData.class);
            if (!getData.isCallback()) {
                Message message = Message.parse(getData.data);
                Optional.ofNullable(message)
                        .ifPresent(message1 ->
                                eventBus.broadcast(MessageEvent.parse(message1)
                                )
                        );
            } else {
                if (callbacks.containsKey(getData.syncId)) {
                    try {
                        callbacks.get(getData.syncId).accept(getData.data);
                        callbacks.remove(getData.syncId);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        });
    }
}
