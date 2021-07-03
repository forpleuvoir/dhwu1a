package forpleuvoir.dhwu1a.core.message.messagesender;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.base.MessageSender;
import forpleuvoir.dhwu1a.core.user.Friend;

import static forpleuvoir.dhwu1a.core.common.ApiKey.NICKNAME;
import static forpleuvoir.dhwu1a.core.common.ApiKey.REMARK;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messagesender
 * <p>#class_name FriendMessageSender
 * <p>#create_time 2021/7/1 21:19
 */
public class FriendMessageSender extends MessageSender {

    @SerializedName(NICKNAME)
    public String nickname;
    @SerializedName(REMARK)
    public String remark;

    protected FriendMessageSender(Long id) {
        super(id);
    }

    @Override
    public Friend getUser() {
        return bot.getFriend(this.id);
    }
}
