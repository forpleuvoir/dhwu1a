package forpleuvoir.dhwu1a.core.message;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.message.base.MessageType;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message
 * <p>#class_name OtherClientMessage
 * <p>#create_time 2021/7/1 22:46
 */
public class OtherClientMessage extends Message {
    public OtherClientMessage(JsonObject object) {
        super(MessageType.OtherClientMessage, object);
    }

    @Override
    public void printMessageLog() {

    }

    @Override
    public String toPlainText() {
        return null;
    }
}