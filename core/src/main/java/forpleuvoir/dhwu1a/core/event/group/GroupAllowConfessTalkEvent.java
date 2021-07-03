package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 坦白说
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name GroupAllowConfessTalkEvent
 * <p>#create_time 2021/7/3 23:36
 */
public class GroupAllowConfessTalkEvent extends GroupEvent {
    /**
     * 原本坦白说是否开启
     */
    @SerializedName(ORIGIN)
    public final Boolean origin;
    /**
     * 现在坦白说是否开启
     */
    @SerializedName(CURRENT)
    public final Boolean current;
    /**
     * 坦白说状态改变的群信息
     */
    @SerializedName(GROUP)
    public final GroupData group;
    /**
     * 是否Bot进行该操作
     */
    @SerializedName(IS_BY_BOT)
    public final Boolean isByBot;

    private GroupAllowConfessTalkEvent(Boolean origin, Boolean current,
                                       GroupData group,
                                       Boolean isByBot
    ) {
        super(GroupEventType.GroupAllowConfessTalkEvent);
        this.origin = origin;
        this.current = current;
        this.group = group;
        this.isByBot = isByBot;
    }


    @Nullable
    @Override
    public Group getGroup() {
        return group.getUser();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[origin:%b,current:%b,group:%d,isByBot:%b]", type, origin, current, group.id, isByBot
        );
    }
}
