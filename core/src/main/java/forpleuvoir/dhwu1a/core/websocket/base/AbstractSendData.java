package forpleuvoir.dhwu1a.core.websocket.base;

import com.google.gson.JsonObject;

import java.util.concurrent.atomic.AtomicInteger;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.COMMAND;
import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.SUB_COMMAND;

/**
 * 抽象发送对象
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket.base
 * <p>#class_name AbstractSendData
 * <p>#create_time 2021/6/28 22:59
 */
public abstract class AbstractSendData {
    private final static AtomicInteger SYNC_ID = new AtomicInteger(0);

    private final Integer sendId;

    public AbstractSendData() {
        this.sendId = SYNC_ID.get();
        SYNC_ID.set(SYNC_ID.get() + 1);
        if (SYNC_ID.get() == 9999) {
            SYNC_ID.set(0);
        }

    }

    public final String toMessageJsonString() {
        JsonObject object = new JsonObject();
        object.addProperty(ApiKey.SYNC_ID, this.sendId);
        object.addProperty(COMMAND, command());
        object.addProperty(SUB_COMMAND, subCommand());
        object.add(COMMAND, content());
        return object.toString();
    }

    public Integer getSendId() {
        return sendId;
    }

    public abstract JsonObject content();

    public String subCommand() {
        return null;
    }

    public abstract String command();
}
