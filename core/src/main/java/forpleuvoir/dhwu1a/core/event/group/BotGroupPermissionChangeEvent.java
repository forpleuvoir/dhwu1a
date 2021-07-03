package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * Bot在群里的权限被改变. 操作人一定是群主
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name BotGroupPermissionChangeEvent
 * <p>#create_time 2021/7/3 21:46
 */
public class BotGroupPermissionChangeEvent extends GroupEvent {
    /**
     * Bot的原权限，OWNER、ADMINISTRATOR或MEMBER
     */
    @SerializedName(ORIGIN)
    public final Permission origin;
    /**
     * Bot的新权限，OWNER、ADMINISTRATOR或MEMBER
     */
    @SerializedName(CURRENT)
    public final Permission current;
    /**
     * 权限改变所在的群信息
     */
    @SerializedName(GROUP)
    public final GroupData group;

    private BotGroupPermissionChangeEvent(Permission origin,
                                          Permission current,
                                          GroupData group
    ) {
        super(GroupEventType.BotGroupPermissionChangeEvent);
        this.origin = origin;
        this.current = current;
        this.group = group;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return group.getUser();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[origin:%s,current:%s,group:%d]", type, origin, current, group.id);
    }
}
