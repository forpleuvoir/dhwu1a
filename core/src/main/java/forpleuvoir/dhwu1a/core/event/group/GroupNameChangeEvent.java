package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.common.data.OperatorData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 某个群名改变
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name GroupNameChangeEvent
 * <p>#create_time 2021/7/3 23:18
 */
public class GroupNameChangeEvent extends GroupEvent {
    /**
     * 原群名
     */
    @SerializedName(ORIGIN)
    public final String origin;
    /**
     * 新群名
     */
    @SerializedName(CURRENT)
    public final String current;
    /**
     * 群名改名的群信息
     */
    @SerializedName(GROUP)
    public final GroupData group;
    /**
     * 操作的管理员或群主信息，当null时为Bot操作
     */
    @Nullable
    @SerializedName(OPERATOR)
    public final OperatorData operator;

    private GroupNameChangeEvent(String origin, String current,
                                 GroupData group,
                                 @Nullable OperatorData operator
    ) {
        super(GroupEventType.GroupNameChangeEvent);
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