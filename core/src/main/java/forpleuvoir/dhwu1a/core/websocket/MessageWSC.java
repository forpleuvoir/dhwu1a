package forpleuvoir.dhwu1a.core.websocket;

import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.URLUtils;
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient;
import forpleuvoir.dhwu1a.core.websocket.base.ISendObject;

import java.net.URISyntaxException;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

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

    public MessageWSC(Bot bot, String ip, int port, String verifyKey) throws URISyntaxException {
        super(String.format("%s/%s?%s=%s&%s=%d",
                            URLUtils.getWSURL(ip, port),
                            MESSAGE,
                            VERIFY_KEY, verifyKey,
                            QQ, bot.id
              ),bot,MESSAGE
        );
    }

    public void sendMessage(ISendObject sendObject){
        this.send(sendObject.toMessageJsonString());
    }

    @Override
    public void onMessage(String message) {
        if (JsonUtil.hasKey(message, SYNC_ID)){


        }
    }
}
