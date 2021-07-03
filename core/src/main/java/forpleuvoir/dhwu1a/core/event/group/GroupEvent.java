package forpleuvoir.dhwu1a.core.event.group;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.ReflectionUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.TYPE;

/**
 * 群事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name GroupEvent
 * <p>#create_time 2021/7/3 21:45
 */
public abstract class GroupEvent extends Dhwu1aEvent {
    /**
     * 事件类型
     */
    @SerializedName(TYPE)
    public final GroupEventType type;

    public static GroupEvent parse(JsonObject object) {
        var type = GroupEventType.valueOf(object.get(TYPE).getAsString());
        return (GroupEvent) JsonUtil.gson.fromJson(object, type.getClazz()).initialize();
    }

    protected GroupEvent(GroupEventType type) {
        this.type = type;
    }

    @Nullable
    public abstract Group getGroup();

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void broadcastHandle(
            ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? extends Dhwu1aEvent>>> eventListeners
    ) {
        super.broadcastHandle(eventListeners);
        if (ReflectionUtil.isExtended(this.getClass(), GroupEvent.class)) {
            if (eventListeners.containsKey(GroupEvent.class)) {
                for (Consumer listener : eventListeners.get(GroupEvent.class)) {
                    listener.accept(this);
                }
            }
        }
    }
}
