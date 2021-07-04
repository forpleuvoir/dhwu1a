package forpleuvoir.dhwu1a.core.user.base;

import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name IUser
 * <p>#create_time 2021/6/29 22:31
 */
public interface IUser extends IJsonData {

    void sendMessage(Consumer<Integer> messageId, @Nonnull List<MessageItem> messageChain);

    void sendMessage(@Nonnull List<MessageItem> messageChain);

    default void sendMessage(@Nonnull MessageItem... messageChain) {
        sendMessage(Arrays.asList(messageChain));
    }

    default void sendMessage(Consumer<Integer> messageId, @Nonnull MessageItem... messageChain) {
        sendMessage(messageId, Arrays.asList(messageChain));
    }

    void quote(Integer quoteId, Consumer<Integer> messageId, @Nonnull List<MessageItem> messageChain);

    default void quote(Integer quoteId, Consumer<Integer> messageId, @Nonnull MessageItem... messageChain) {
        quote(quoteId, messageId, Arrays.asList(messageChain));
    }
}
