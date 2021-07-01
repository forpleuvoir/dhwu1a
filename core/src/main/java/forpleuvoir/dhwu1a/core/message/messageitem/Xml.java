package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.XML;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Xml
 * <p>#create_time 2021/6/30 0:27
 */
public class Xml extends MessageItem {
    /**
     * XML文本
     */
    @SerializedName(XML)
    public final String xml;

    public Xml(String xml) {
        super(MessageItemType.Xml);
        this.xml = xml;
    }

    @Override
    public String toPlainText() {
        return "[XML]";
    }
}
