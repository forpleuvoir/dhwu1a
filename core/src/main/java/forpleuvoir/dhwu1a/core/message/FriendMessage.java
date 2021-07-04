package forpleuvoir.dhwu1a.core.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.message.base.MessageType;
import forpleuvoir.dhwu1a.core.user.Friend;

import static forpleuvoir.dhwu1a.core.common.ApiKey.FRIEND;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message
 * <p>#class_name FriendMessage
 * <p>#create_time 2021/7/1 1:29
 */
public class FriendMessage extends Message {
    /**
     * 发送消息的好友
     */
    @SerializedName(FRIEND)
    public final Friend friend;

    public FriendMessage(JsonObject object) {
        super(MessageType.FriendMessage, object);
        this.friend = (Friend) sender.getUser();
    }

    @Override
    public void printMessageLog() {
        log.info("RM/Bot.{}:{}({}) >> {}", bot.id, friend.data.nickname, friend.id, this.toPlainText());
    }
}
