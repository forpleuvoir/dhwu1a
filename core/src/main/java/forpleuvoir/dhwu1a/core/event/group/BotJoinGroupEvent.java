package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.GROUP;

/**
 * Bot加入了一个新群
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name BotJoinGroupEvent
 * <p>#create_time 2021/7/3 23:02
 */
public class BotJoinGroupEvent extends GroupEvent {

    /**
     * Bot新加入群的信息
     */
    @SerializedName(GROUP)
    public final GroupData group;

    private BotJoinGroupEvent(GroupData group) {
        super(GroupEventType.BotJoinGroupEvent);
        this.group = group;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return group.getUser();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[group:%d]", type, group.id);
    }
}
