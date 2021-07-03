package forpleuvoir.dhwu1a.core.event.group;


import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.OperatorData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.DURATION_SECONDS;
import static forpleuvoir.dhwu1a.core.common.ApiKey.OPERATOR;

/**
 * Bot被禁言
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name BotMuteEvent
 * <p>#create_time 2021/7/3 22:44
 */
public class BotMuteEvent extends GroupEvent {

    /**
     * 禁言时长，单位为秒
     */
    @SerializedName(DURATION_SECONDS)
    public final Integer durationSeconds;
    /**
     * 操作的管理员或群主信息
     */
    @SerializedName(OPERATOR)
    public final OperatorData operator;

    private BotMuteEvent(Integer durationSeconds,
                         OperatorData operator
    ) {
        super(GroupEventType.BotMuteEvent);
        this.durationSeconds = durationSeconds;
        this.operator = operator;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return operator.getGroup();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[time:%d,operator:%d,group:%d]", type, durationSeconds, operator.id,
                             operator.group.id
        );
    }
}
