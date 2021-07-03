package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.common.ApiKey.CONTENT;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name App
 * <p>#create_time 2021/6/30 0:28
 */
public class App extends MessageItem {
    /**
     * App内容
     */
    @SerializedName(CONTENT)
    public final String content;

    public App(String content) {
        super(MessageItemType.App);
        this.content = content;
    }

    @Override
    public String toPlainText() {
        return "[APP]";
    }
}
