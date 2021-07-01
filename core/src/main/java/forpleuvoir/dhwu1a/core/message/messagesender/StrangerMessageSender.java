package forpleuvoir.dhwu1a.core.message.messagesender;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.base.MessageSender;
import forpleuvoir.dhwu1a.core.user.base.User;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.NICKNAME;
import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.REMARK;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messagesender
 * <p>#class_name StrangerMessageSender
 * <p>#create_time 2021/7/1 22:41
 */
public class StrangerMessageSender extends MessageSender {
    @SerializedName(NICKNAME)
    public String nickname;
    @SerializedName(REMARK)
    public String remark;

    protected StrangerMessageSender(Long id) {
        super(id);
    }

    @Nullable
    @Override
    public User getUser() {
        return null;
    }
}
