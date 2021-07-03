package forpleuvoir.dhwu1a.core.websocket;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.URLUtils;
import forpleuvoir.dhwu1a.core.websocket.base.AbstractSendData;
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient;

import java.net.URISyntaxException;
import java.util.Objects;
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

    private final ConcurrentHashMap<Integer, Consumer<GetData>> callbacks = new ConcurrentHashMap<>();

    public static MessageWSC getInstance(Bot bot, String ip, int port, String verifyKey) {
        try {
            return new MessageWSC(bot, ip, port, verifyKey);
        } catch (URISyntaxException e) {
            e.printStackTrace();
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

    public void sendMessage(AbstractSendData sendObject, Consumer<GetData> callback) {
        this.send(sendObject.toMessageJsonString());
        callbacks.put(sendObject.getSendId(), callback);

    }

    @Override
    public void onMessage(String data) {
        JsonObject jsonObject = JsonUtil.ifHasKey(data, SYNC_ID);
        if (jsonObject != null) {
            GetData getData = JsonUtil.gson.fromJson(jsonObject, GetData.class);
            if (!getData.isCallback()) {
                Message message = Message.parse(getData.data);
                switch (Objects.requireNonNull(message).type) {
                    //todo 消息事件发布
                }

            } else {
                if (callbacks.containsKey(getData.syncId)) {
                    callbacks.get(getData.syncId).accept(getData);
                    callbacks.remove(getData.syncId);
                }
            }
        }
    }
}
