package forpleuvoir.dhwu1a.core.message;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.message.base.MessageType;
import forpleuvoir.dhwu1a.core.message.messagesender.StrangerMessageSender;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message
 * <p>#class_name StrangerMessage
 * <p>#create_time 2021/7/1 22:18
 */
public class StrangerMessage extends Message {


    public StrangerMessage(JsonObject object) {
        super(MessageType.StrangerMessage, object);
    }

    @Override
    public void printMessageLog() {
        log.info("M/Bot.{} 陌生人[{}({})] >> {}", bot.id, ((StrangerMessageSender) sender).nickname, sender.id,
                 toPlainText()
        );
    }

}
