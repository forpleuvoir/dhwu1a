package forpleuvoir.dhwu1a.core.message.messageitem;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Image
 * <p>#create_time 2021/6/30 0:26
 */
public class Image extends MessageItem{
    public Image(MessageItemType type) {
        super(type);
    }

    @Override
    public String toPlainText() {
        return null;
    }
}
