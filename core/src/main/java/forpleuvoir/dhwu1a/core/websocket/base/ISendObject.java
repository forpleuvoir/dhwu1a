package forpleuvoir.dhwu1a.core.websocket.base;

import com.google.gson.JsonObject;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * 抽象发送对象
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.websocket.base
 * <p>#class_name AbstractSendObject
 * <p>#create_time 2021/6/28 22:59
 */
public interface ISendObject {

    default String toMessageJsonString() {
        JsonObject object = new JsonObject();
        object.addProperty(SYNC_ID, syncId());
        object.addProperty(COMMAND, command());
        object.addProperty(SUB_COMMAND, command());
        object.add(COMMAND, content());
        return object.toString();
    }

    int syncId();

    JsonObject content();

    default String subCommand() {
        return null;
    }

    String command();
}
