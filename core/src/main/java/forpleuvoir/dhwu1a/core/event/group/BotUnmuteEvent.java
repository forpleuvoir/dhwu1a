package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.OperatorData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.OPERATOR;

/**
 * Bot被取消禁言
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name BotUnmuteEvent
 * <p>#create_time 2021/7/3 22:59
 */
public class BotUnmuteEvent extends GroupEvent {

    /**
     * 操作的管理员或群主信息
     */
    @SerializedName(OPERATOR)
    public final OperatorData operator;

    private BotUnmuteEvent(OperatorData operator) {
        super(GroupEventType.BotUnmuteEvent);
        this.operator = operator;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return operator.getGroup();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[operator:%d,group:%d]", type, operator.id, operator.group.id);
    }
}
