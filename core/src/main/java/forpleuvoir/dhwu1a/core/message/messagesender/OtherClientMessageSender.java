package forpleuvoir.dhwu1a.core.message.messagesender;

import forpleuvoir.dhwu1a.core.message.base.MessageSender;
import forpleuvoir.dhwu1a.core.user.base.User;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.PLATFORM;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messagesender
 * <p>#class_name OtherClientMessageSender
 * <p>#create_time 2021/7/1 22:43
 */
public class OtherClientMessageSender extends MessageSender {
    /**
     * 平台
     */
    @SuppressWarnings(PLATFORM)
    public String platform;

    protected OtherClientMessageSender(Long id) {
        super(id);
    }

    @Nullable
    @Override
    public User getUser() {
        return null;
    }
}
