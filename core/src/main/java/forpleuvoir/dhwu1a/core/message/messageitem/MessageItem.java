package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.message.base.IPlainText;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name MessageItem
 * <p>#create_time 2021/6/29 23:02
 */
public abstract class MessageItem implements IPlainText, IJsonData {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(MessageItem.class);
    @SerializedName(TYPE)
    protected final MessageItemType type;

    public MessageItem(JsonObject jsonObject) {
        this.type = MessageItemType.valueOf(jsonObject.get(TYPE).getAsString());
    }

    public MessageItem(MessageItemType type) {
        this.type = type;
    }

    public static MessageItem parse(JsonObject jsonObject) {
        MessageItemType messageItemType = MessageItemType.valueOf(jsonObject.get(TYPE).getAsString());
        return JsonUtil.gson.fromJson(jsonObject, messageItemType.getClazz());
    }
}
