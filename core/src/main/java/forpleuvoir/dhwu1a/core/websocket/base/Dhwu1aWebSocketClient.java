package forpleuvoir.dhwu1a.core.websocket.base;

import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

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
    private transient static final Dhwu1aLog log = new Dhwu1aLog(Dhwu1aWebSocketClient.class);
    private transient OnWebSocketOpened onWebSocketOpened;
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
                 handshakeData.getHttpStatusMessage()
        );
        if (onWebSocketOpened != null) {
            onWebSocketOpened.invoke();
        }
    }

    public void setOnOpenCallback(OnWebSocketOpened callback) {
        this.onWebSocketOpened = callback;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("WebSocketClient {} 关闭", this.name);
        if (Dhwu1a.getInstance().isRunning())
            new Thread(this::reconnect).start();
    }


    @Override
    public void onError(Exception ex) {
        log.error(ex.getMessage(), ex);
    }
}
