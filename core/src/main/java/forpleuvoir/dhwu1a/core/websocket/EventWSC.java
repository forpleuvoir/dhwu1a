package forpleuvoir.dhwu1a.core.websocket;

import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.URLUtils;
import forpleuvoir.dhwu1a.core.websocket.base.Dhwu1aWebSocketClient;

import java.net.URISyntaxException;

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

    public static EventWSC getInstance(Bot bot, String ip, int port, String verifyKey) {
        try {
            return new EventWSC(bot, ip, port, verifyKey);
        } catch (URISyntaxException e) {
            e.printStackTrace();
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
    public void onMessage(String message) {
        if (JsonUtil.hasKey(message, SYNC_ID)){


        }
    }
}
