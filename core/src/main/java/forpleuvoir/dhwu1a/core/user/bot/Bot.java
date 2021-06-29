package forpleuvoir.dhwu1a.core.user.bot;

import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.websocket.EventWSC;
import forpleuvoir.dhwu1a.core.websocket.MessageWSC;

import java.net.URISyntaxException;

/**
 * bot本体
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user.bot
 * <p>#class_name Bot
 * <p>#create_time 2021/6/28 20:38
 */
public class Bot {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(Bot.class);
    /**
     * Bot的QQ号
     */
    public final long id;
    /**
     * 消息websocket客户端
     */
    private final MessageWSC messageWSC;
    private final EventWSC eventWSC;


    public Bot(Dhwu1aConfig config) throws URISyntaxException {
        this.id = config.botId;
        this.messageWSC = new MessageWSC(this, config.ip, config.port, config.verifyKey);
        this.eventWSC = new EventWSC(this, config.ip, config.port, config.verifyKey);
    }

    public void initialize(){
        this.messageWSC.connect();
        this.eventWSC.connect();
    }
}
