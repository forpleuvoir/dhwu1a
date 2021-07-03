package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.common.data.OperatorData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 全员禁言
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name GroupMuteAllEvent
 * <p>#create_time 2021/7/3 23:29
 */
public class GroupMuteAllEvent extends GroupEvent {

    /**
     * 原本是否处于全员禁言
     */
    @SerializedName(ORIGIN)
    public final Boolean origin;
    /**
     * 现在是否处于全员禁言
     */
    @SerializedName(CURRENT)
    public final Boolean current;
    /**
     * 全员禁言的群信息
     */
    @SerializedName(GROUP)
    public final GroupData group;
    /**
     * 操作的管理员或群主信息，当null时为Bot操作
     */
    @Nullable
    @SerializedName(OPERATOR)
    public final OperatorData operator;

    private GroupMuteAllEvent(Boolean origin, Boolean current,
                              GroupData group,
                              @Nullable OperatorData operator
    ) {
        super(GroupEventType.GroupMuteAllEvent);
        this.origin = origin;
        this.current = current;
        this.group = group;
        this.operator = operator;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return group.getUser();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[origin:%s,current:%s,group:%d,operator:%d]", type, origin, current, group.id,
                             operator == null ? bot.id : operator.id
        );
    }
}
