package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.websocket.base.ApiKey;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;
/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Source
 * <p>#create_time 2021/6/30 00:11
 */
public class Source extends MessageItem {
    /**
     * 消息的识别号，用于引用回复（Source类型永远为chain的第一个元素）
     */
    @SerializedName(ID)
    public final Integer id;
    @SerializedName(ApiKey.TIME)
    public final Integer time;


    public Source(JsonObject object) {
        super(object);
        this.id = object.get(ID).getAsInt();
        this.time = object.get(TIME).getAsInt();
    }

    @Override
    public String toPlainText() {
        return JsonUtil.toJsonStr(this);
    }
}
