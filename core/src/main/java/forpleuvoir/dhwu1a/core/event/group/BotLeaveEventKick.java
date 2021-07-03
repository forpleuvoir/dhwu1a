package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.GROUP;

/**
 * Bot被踢出一个群
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name BotLeaveEventKick
 * <p>#create_time 2021/7/3 23:09
 */
public class BotLeaveEventKick extends GroupEvent {

    /**
     * Bot被踢出的群信息
     */
    @SerializedName(GROUP)
    public final GroupData group;

    private BotLeaveEventKick(GroupData group) {
        super(GroupEventType.BotLeaveEventKick);
        this.group = group;
    }

    /**
     * 退出群之后则不再能获取到该群对象
     *
     * @return null
     */
    @Nullable
    @Override
    public Group getGroup() {
        return null;
    }

    @Override
    public String toPlainText() {
        return String.format("%s[group:%d]", type, group.id);
    }
}
