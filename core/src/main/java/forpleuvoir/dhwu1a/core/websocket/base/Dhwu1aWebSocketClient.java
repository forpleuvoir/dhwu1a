package forpleuvoir.dhwu1a.core.websocket.base;

import forpleuvoir.dhwu1a.core.user.Bot;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * websocket客户端
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket.base
 * <p>#class_name Dhwu1aWebSocketClient
 * <p>#create_time 2021/6/28 20:48
 */
public abstract class Dhwu1aWebSocketClient extends WebSocketClient {
    private transient static final Logger log = LoggerFactory.getLogger(Dhwu1aWebSocketClient.class);
    protected final Bot bot;
    protected final String name;

    public Dhwu1aWebSocketClient(String serverUri, Bot bot, String name) throws URISyntaxException {
        super(new URI(serverUri));
        this.bot = bot;
        this.name = name;
    }


    @Override
    public void onOpen(ServerHandshake handshakeData) {
        Thread.currentThread().setName(name);
        log.info("WebSocketClient 初始化[code:{},message:{}]", handshakeData.getHttpStatus(),
                 handshakeData.getHttpStatusMessage());
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("WebSocketClient {} 关闭",this.name);
    }


    @Override
    public void onError(Exception ex) {
        log.error(ex.getMessage(), ex);
    }
}
