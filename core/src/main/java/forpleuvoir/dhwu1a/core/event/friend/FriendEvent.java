package forpleuvoir.dhwu1a.core.event.friend;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.FriendData;
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent;
import forpleuvoir.dhwu1a.core.user.Friend;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.FRIEND;
import static forpleuvoir.dhwu1a.core.common.ApiKey.TYPE;

/**
 * 好友事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.friend
 * <p>#class_name FriendEvent
 * <p>#create_time 2021/7/3 20:55
 */
public abstract class FriendEvent extends Dhwu1aEvent {
    /**
     * 好友事件类型
     */
    @SerializedName(TYPE)
    public final FriendEventType type;
    /**
     * 事件相关好友
     */
    @SerializedName(FRIEND)
    @Nullable
    public final FriendData friend;

    public static FriendEvent parse(JsonObject object) {
        var type = FriendEventType.valueOf(object.get(TYPE).getAsString());
        return (FriendEvent) JsonUtil.gson.fromJson(object, type.getClazz()).initialize();
    }

    protected FriendEvent(FriendEventType type, @Nullable FriendData friend) {
        this.type = type;
        this.friend = friend;
    }

    @Nullable
    public Friend getFriend() {
        AtomicReference<Friend> f = new AtomicReference<>();
        Optional.ofNullable(friend).ifPresent(friendData -> f.set(friendData.getUser()));
        return f.get();
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void broadcastHandle(
            @NotNull ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? super Dhwu1aEvent>>> eventListeners
    ) {
        super.broadcastHandle(eventListeners);
        if (ReflectionUtil.isExtended(this.getClass(), FriendEvent.class)) {
            if (eventListeners.containsKey(FriendEvent.class)) {
                for (Consumer listener : eventListeners.get(FriendEvent.class)) {
                    listener.accept(this);
                }
            }
        }
    }

}
