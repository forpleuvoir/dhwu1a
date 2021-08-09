package forpleuvoir.dhwu1a.core.event.request;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 申请事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.request
 * <p>#class_name RequestEvent
 * <p>#create_time 2021/7/4 1:42
 */
public abstract class RequestEvent extends Dhwu1aEvent {
    /**
     * 事件类型
     */
    @SerializedName(TYPE)
    public final RequestEventType type;
    /**
     * 事件标识，响应该事件时的标识
     */
    @SerializedName(EVENT_ID)
    public final Long eventId;
    /**
     * 申请人QQ号
     */
    @SerializedName(FROM_ID)
    public final Long fromId;
    /**
     * 相关群号 没有则为0
     */
    @SerializedName(GROUP_ID)
    public final Long groupId;
    /**
     * 邀请人（好友）的昵称或群名片
     */
    @SerializedName(NICK)
    public final String nick;
    /**
     * 邀请消息
     */
    @SerializedName(MESSAGE)
    public final String message;

    public static RequestEvent parse(JsonObject object) {
        var type = RequestEventType.valueOf(object.get(TYPE).getAsString());
        return (RequestEvent) JsonUtil.gson.fromJson(object, type.getClazz()).initialize();
    }


    protected RequestEvent(RequestEventType type, Long eventId, Long fromId, Long groupId, String nick, String message
    ) {
        this.type = type;
        this.eventId = eventId;
        this.fromId = fromId;
        this.groupId = groupId;
        this.nick = nick;
        this.message = message;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void broadcastHandle(
            @NotNull ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? super Dhwu1aEvent>>> eventListeners
    ) {
        super.broadcastHandle(eventListeners);
        if (ReflectionUtil.isExtended(this.getClass(), RequestEvent.class)) {
            if (eventListeners.containsKey(RequestEvent.class)) {
                for (Consumer listener : eventListeners.get(RequestEvent.class)) {
                    listener.accept(this);
                }
            }
        }
    }

    @Override
    public String toPlainText() {
        return String.format("%s[eventId:%d,fromId:%d,groupId:%d,message:%s]", type, eventId, fromId, groupId, message);
    }
}
