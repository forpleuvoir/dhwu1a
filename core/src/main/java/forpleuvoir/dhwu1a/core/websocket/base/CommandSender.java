package forpleuvoir.dhwu1a.core.websocket.base;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.common.ApiKey;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.websocket.command.Command;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 命令发送器
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket.base
 * <p>#class_name CommandSender
 * <p>#create_time 2021/6/28 22:59
 */
public class CommandSender {
    private final static AtomicInteger SYNC_ID = new AtomicInteger(0);
    private final Integer sendId;
    private String command;
    private String subCommand = null;
    private JsonObject content;

    public CommandSender(Command command, JsonObject content) {
        this();
        this.command = command.getCommand();
        this.content = content;
    }

    public CommandSender(Command command) {
        this();
        this.command = command.getCommand();
        this.content = null;
    }

    public CommandSender(Command command, Map<?, ?> map) {
        this();
        this.command = command.getCommand();
        this.content = JsonUtil.toJsonObject(map).getAsJsonObject();
    }

    public CommandSender(String command, String subCommand, JsonObject content) {
        this();
        this.command = command;
        this.subCommand = subCommand;
        this.content = content;
    }

    private CommandSender() {
        this.sendId = SYNC_ID.get();
        SYNC_ID.set(SYNC_ID.get() + 1);
        if (SYNC_ID.get() == 9999) {
            SYNC_ID.set(0);
        }

    }

    public final String toMessageJsonString() {
        JsonObject object = new JsonObject();
        object.addProperty(ApiKey.SYNC_ID, this.sendId);
        object.addProperty(COMMAND, this.command);
        object.addProperty(SUB_COMMAND, this.subCommand);
        object.add(CONTENT, this.content);
        return object.toString();
    }

    public Integer getSendId() {
        return sendId;
    }

}
