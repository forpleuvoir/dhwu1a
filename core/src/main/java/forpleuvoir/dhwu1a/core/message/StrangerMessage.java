package forpleuvoir.dhwu1a.core.message;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.message.base.MessageType;

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

    }

    @Override
    public String toPlainText() {
        return null;
    }
}
