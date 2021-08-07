package forpleuvoir.dhwu1a.core.message.base;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.user.base.User;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.JsonUtil;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.ID;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.base
 * <p>#class_name MessageSender
 * <p>#create_time 2021/7/1 21:15
 */
public abstract class MessageSender implements IJsonData {
    /**
     * 接收消息的Bot
     */
    public static transient final Bot bot = Dhwu1a.getInstance().getBot();
    /**
     * 发送者QQ号
     */
    @SerializedName(ID)
    public final Long id;

    public static MessageSender parse(MessageType type, JsonObject object) {
        return JsonUtil.gson.fromJson(object, type.getClazz());
    }

    public MessageSender(Long id) {
        this.id = id;
    }

    @Nullable
    public abstract User getUser();
}
