package forpleuvoir.dhwu1a.core.websocket;

import forpleuvoir.dhwu1a.core.user.Bot;
import forpleuvoir.dhwu1a.core.util.URLUtils;
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient;

import java.net.URISyntaxException;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

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
    public void onMessage(String message) {

    }
}
