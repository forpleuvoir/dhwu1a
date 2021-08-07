package forpleuvoir.dhwu1a.core.user.bot;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.common.data.FriendData;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig;
import forpleuvoir.dhwu1a.core.user.Friend;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.base.Profile;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.websocket.EventWSC;
import forpleuvoir.dhwu1a.core.websocket.MessageWSC;
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender;
import forpleuvoir.dhwu1a.core.websocket.command.Command;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.DATA;
import static forpleuvoir.dhwu1a.core.common.ApiKey.TARGET;

/**
 * bot本体
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user.bot
 * <p>#class_name Bot
 * <p>#create_time 2021/6/28 20:38
 */
public class Bot implements IJsonData {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(Bot.class);
    private static Bot INSTANCE;

    public static void initialize(Dhwu1aConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new Bot(config);
            INSTANCE.init();
        }
    }

    public static Bot getInstance() {
        return INSTANCE;
    }

    /**
     * Bot的QQ号
     */
    public final long id;
    /**
     * Bot资料
     */
    private Profile profile;
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

    private Bot(Dhwu1aConfig config) {
        this.id = config.botId;
        this.messageWSC = MessageWSC.getInstance(this, config.ip, config.port, config.verifyKey);
        this.eventWSC = EventWSC.getInstance(this, config.ip, config.port, config.verifyKey);
    }

    private void init() {
        this.eventWSC.connect();
        try {
            this.messageWSC.connectBlocking();
            sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void sync() {
        syncGroup();
        syncFriend();
        syncProfile();
    }

    public void syncFriend() {
        log.info("同步好友列表");
        sendCommand(new CommandSender(Command.friendList), data -> {
            if (data.get(DATA).isJsonArray()) {
                friends.clear();
                for (JsonElement element : data.get(DATA).getAsJsonArray()) {
                    friends.add(new Friend(JsonUtil.gson.fromJson(element, FriendData.class)));
                }
            }
        });
    }

    public void syncGroup() {
        log.info("同步群列表");
        sendCommand(new CommandSender(Command.groupList), data -> {
            if (data.get(DATA).isJsonArray()) {
                groups.clear();
                for (JsonElement datum : data.get(DATA).getAsJsonArray()) {
                    GroupData groupData = JsonUtil.gson.fromJson(datum, GroupData.class);
                    groups.add(new Group(groupData));
                }
            }
        });
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息ID
     */
    public void recall(Integer messageId) {
        sendCommand(new CommandSender(Command.recall, Map.of(TARGET, messageId)),
                null
        );
    }

    public void syncProfile() {
        log.info("同步Bot资料");
        sendCommand(new CommandSender(Command.botProfile),
                data -> this.profile = JsonUtil.gson.fromJson(data, Profile.class)
        );
    }

    public void sendCommand(CommandSender sender, Consumer<JsonObject> consumer) {
        messageWSC.sendMessage(sender, consumer);
    }

    @Nullable
    public Friend getFriend(Long id) {
        return friends.stream().filter(friend -> friend.id.equals(id)).findFirst().orElse(null);
    }

    @Nullable
    public Group getGroup(Long id) {
        return groups.stream().filter(group -> group.id.equals(id)).findFirst().orElse(null);
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public String toJsonString() {
        return profile.toJsonString();
    }

    public void close() {
        messageWSC.close();
        eventWSC.close();
    }
}
