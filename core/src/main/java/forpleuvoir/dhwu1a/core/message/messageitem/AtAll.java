package forpleuvoir.dhwu1a.core.message.messageitem;

import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name AtAll
 * <p>#create_time 2021/6/30 0:25
 */
public class AtAll extends MessageItem {
    public AtAll() {
        super(MessageItemType.AtAll);
    }

    @Override
    public String toPlainText() {
        return null;
    }
}
