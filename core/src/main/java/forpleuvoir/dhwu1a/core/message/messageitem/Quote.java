package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import java.util.List;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Quote
 * <p>#create_time 2021/6/30 00:10
 */
public class Quote extends MessageItem {
    /**
     * 被引用回复的原消息的messageId
     */
    @SerializedName(ID)
    public final long id;
    /**
     * 被引用回复的原消息所接收的群号，当为好友消息时为0
     */
    @SerializedName(GROUP_ID)
    public final long groupId;
    /**
     * 被引用回复的原消息的发送者的QQ号
     */
    @SerializedName(SENDER_ID)
    public final long senderId;
    /**
     * 被引用回复的原消息的接收者者的QQ号（或群号）
     */
    @SerializedName(TARGET_ID)
    public final long targetId;
    /**
     * 被引用回复的原消息的消息链对象
     */
    @SerializedName(ORIGIN)
    public final List<Plain> origin;

    public Quote(long id, long groupId, long senderId, long targetId,
                 List<Plain> origin
    ) {
        super(MessageItemType.Quote);
        this.id = id;
        this.groupId = groupId;
        this.senderId = senderId;
        this.targetId = targetId;
        this.origin = origin;
    }

    @Override
    public String toPlainText() {
        return String.format("回复[%s]:", getOriginPlainText());
    }

    private String getOriginPlainText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Plain plain : origin) {
            stringBuilder.append(plain.toPlainText());
        }
        return stringBuilder.toString();
    }
}
