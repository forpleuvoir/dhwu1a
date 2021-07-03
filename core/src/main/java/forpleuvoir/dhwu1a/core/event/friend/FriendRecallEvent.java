package forpleuvoir.dhwu1a.core.event.friend;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.user.Friend;

import javax.annotation.Nullable;
import java.util.Optional;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 好友消息撤回
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.friend
 * <p>#class_name FriendRecallEvent
 * <p>#create_time 2021/7/3 22:12
 */
public class FriendRecallEvent extends FriendEvent {
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
     * 好友QQ号或BotQQ号
     */
    @SerializedName(OPERATOR)
    public final Long operator;


    public FriendRecallEvent(Long authorId, Integer messageId,
                             Integer time,
                             Long operator
    ) {
        super(FriendEventType.FriendRecallEvent, null);
        this.authorId = authorId;
        this.messageId = messageId;
        this.time = time;
        this.operator = operator;
    }

    @Nullable
    @Override
    public Friend getFriend() {
        return Optional.ofNullable(bot.getFriend(authorId)).orElse(null);
    }

    @Override
    public String toPlainText() {
        return String.format("%s[messageId:%d]", type, messageId);
    }
}
