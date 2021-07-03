package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.common.data.OperatorData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 群消息撤回
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name GroupRecallEvent
 * <p>#create_time 2021/7/3 23:11
 */
public class GroupRecallEvent extends GroupEvent {
    /**
     * 原消息发送者的QQ号
     */
    @SerializedName(AUTHOR_ID)
    public final Long authorId;
    /**
     * 原消息messageId
     */
    @SerializedName(MESSAGE_ID)
    public final Integer messageId;
    /**
     * 原消息发送时间
     */
    @SerializedName(TIME)
    public final Integer time;
    /**
     * 消息撤回所在的群
     */
    @SerializedName(GROUP)
    public final GroupData group;
    /**
     * 撤回消息的操作人，当null时为bot操作
     */
    @Nullable
    @SerializedName(OPERATOR)
    public final OperatorData operator;

    private GroupRecallEvent(Long authorId, Integer messageId, Integer time,
                             GroupData group,
                             @Nullable OperatorData operator
    ) {
        super(GroupEventType.GroupRecallEvent);
        this.authorId = authorId;
        this.messageId = messageId;
        this.time = time;
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
        return String.format("%s[messageId:%d,group:%d,operator:%d]", type, messageId, group.id,
                             operator == null ? bot.id : operator.id
        );
    }
}
