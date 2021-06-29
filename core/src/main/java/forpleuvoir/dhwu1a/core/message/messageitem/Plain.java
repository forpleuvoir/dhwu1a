package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.websocket.base.ApiKey;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Plain
 * <p>#create_time 2021/6/30 0:26
 */
public class Plain extends MessageItem {
    /**
     * 文字消息
     */
    @SerializedName(ApiKey.TEXT)
    public final String text;


    public Plain(String text) {
        super(MessageItemType.Plain);
        this.text = text;
    }

    @Override
    public String toPlainText() {
        return null;
    }
}
