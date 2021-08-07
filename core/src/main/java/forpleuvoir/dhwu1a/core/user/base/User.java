package forpleuvoir.dhwu1a.core.user.base;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.user.bot.Bot;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.ID;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name User
 * <p>#create_time 2021/6/29 22:32
 */
public abstract class User implements IUser {

    public final Bot bot = Dhwu1a.getInstance().getBot();
    /**
     * 用户ID(QQ号)
     */
    @SerializedName(ID)
    public final Long id;

    protected User(Long id) {
        this.id = id;
    }

    public void sendMessage(@Nonnull List<MessageItem> messageChain) {
        messageSenderObject().sendMessage(messageChain, null);
    }

    @Override
    public void sendMessage(Consumer<Integer> messageId, @Nonnull List<MessageItem> messageChain) {
        messageSenderObject().sendMessage(messageChain, messageId);
    }

    @Override
    public void quote(Integer quoteId, Consumer<Integer> messageId, @Nonnull List<MessageItem> messageChain) {
        messageSenderObject().isQuoted(quoteId).sendMessage(messageChain, messageId);
    }

    public abstract MessageSenderObject messageSenderObject();

}
