package forpleuvoir.dhwu1a.core.user.bot;

import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig;
import forpleuvoir.dhwu1a.core.user.Friend;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.websocket.EventWSC;
import forpleuvoir.dhwu1a.core.websocket.MessageWSC;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentLinkedDeque;

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
     * Bot资料
     */
    private BotProfile profile;
    /**
     * 消息websocket客户端
     */
    private final MessageWSC messageWSC;
    /**
     * 事件websocket客户端
     */
    private final EventWSC eventWSC;
    /**
     * Bot的所有好友
     */
    private final ConcurrentLinkedDeque<Friend> friends = new ConcurrentLinkedDeque<>();
    /**
     * Bot加入的所有群
     */
    private final ConcurrentLinkedDeque<Group> groups = new ConcurrentLinkedDeque<>();

    public Bot(Dhwu1aConfig config) {
        this.id = config.botId;
        this.messageWSC = MessageWSC.getInstance(this, config.ip, config.port, config.verifyKey);
        this.eventWSC = EventWSC.getInstance(this, config.ip, config.port, config.verifyKey);
    }

    public void initialize() {
        this.messageWSC.connect();
        this.eventWSC.connect();
    }

    @Nullable
    public Friend getFriend(Long id) {
        return friends.stream().filter(friend -> friend.id.equals(id)).findFirst().orElse(null);
    }

    @Nullable
    public Group getGroup(Long id) {
        return groups.stream().filter(group -> group.id.equals(id)).findFirst().orElse(null);
    }

}
