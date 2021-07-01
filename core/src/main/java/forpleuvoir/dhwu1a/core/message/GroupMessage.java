package forpleuvoir.dhwu1a.core.message;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.message.base.MessageType;
import forpleuvoir.dhwu1a.core.user.Group;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message
 * <p>#class_name GroupMessage
 * <p>#create_time 2021/7/1 20:51
 */
public class GroupMessage extends Message {

    /**
     * 相关的群
     */
    public final Group group;

    public GroupMessage(JsonObject object) {
        super(MessageType.GroupMessage, object);
        this.group = (Group) sender.getUser();
    }

    @Override
    public void printMessageLog() {

    }

}
