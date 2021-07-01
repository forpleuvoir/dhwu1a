package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.JSON;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Json
 * <p>#create_time 2021/6/30 0:27
 */
public class Json extends MessageItem {
    /**
     * Json文本
     */
    @SerializedName(JSON)
    public final String json;

    public Json(String json) {
        super(MessageItemType.Json);
        this.json = json;
    }

    public Json(JsonElement json) {
        super(MessageItemType.Json);
        this.json = JsonUtil.toJsonStr(json);
    }

    @Override
    public String toPlainText() {
        return json;
    }
}
