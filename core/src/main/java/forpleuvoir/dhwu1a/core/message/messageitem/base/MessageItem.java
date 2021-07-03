package forpleuvoir.dhwu1a.core.message.messageitem.base;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.message.base.IPlainText;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

import static forpleuvoir.dhwu1a.core.common.ApiKey.TYPE;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem.base
 * <p>#class_name MessageItem
 * <p>#create_time 2021/6/29 23:02
 */
public abstract class MessageItem implements IPlainText, IJsonData {
    private transient static final Dhwu1aLog LOG = new Dhwu1aLog(MessageItem.class);
    protected transient Dhwu1aLog log;
    @SerializedName(TYPE)
    public final MessageItemType type;

    public MessageItem(JsonObject jsonObject) {
        this.type = MessageItemType.valueOf(jsonObject.get(TYPE).getAsString());
    }

    public MessageItem(MessageItemType type) {
        this.type = type;
    }

    public MessageItem setLog(Dhwu1aLog log) {
        this.log = log;
        return this;
    }

    public static List<MessageItem> parse(JsonArray array) {
        try {
            var list = new LinkedList<MessageItem>();
            for (JsonElement element : array) {
                list.add(parse(element.getAsJsonObject()));
            }
            return list;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    @Nullable
    public static MessageItem parse(JsonObject jsonObject) {
        try {
            MessageItemType messageItemType = MessageItemType.valueOf(jsonObject.get(TYPE).getAsString());
            return JsonUtil.gson.fromJson(jsonObject, messageItemType.getClazz())
                                .setLog(new Dhwu1aLog(messageItemType.getClazz()));
        } catch (Exception e) {
            LOG.error("消息解析失败");
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}
