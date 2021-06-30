package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name At
 * <p>#create_time 2021/6/30 0:11
 */
public class At extends MessageItem {
    /**
     * 群员QQ号
     */
    @SerializedName(TARGET)
    public final long target;
    /**
     * At时显示的文字，发送消息时无效，自动使用群名片
     */
    @SerializedName(DISPLAY)
    public final String display;

    public At(long target, String display) {
        super(MessageItemType.At);
        this.target = target;
        this.display = display;
    }


    @Override
    public String toPlainText() {
        return null;
    }
}
